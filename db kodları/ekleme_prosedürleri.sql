----------------------------
--İÇERİK EKLEME PROSEDÜRLERİ
----------------------------

--Bu prosedürün amacı painting content eklemektir
CREATE OR REPLACE PROCEDURE 
	add_painting_content(
		p_title TEXT,
		p_price DECIMAL(10, 2),
		p_content_creator_id UUID,
		p_image_url TEXT,
		p_style TEXT,
		p_images_size BIGINT DEFAULT 0,
		p_description TEXT DEFAULT 'Açıklama girilmedi'
	)
LANGUAGE
	plpgsql
AS $$
--ihtiyacımız olacak değişkenler
DECLARE
	v_content_creator_user_id UUID;
	v_created_image_id UUID;
	v_created_content_id UUID;
BEGIN
	--İçeriği ekleyen kullanıcının id'sini alalım
	SELECT
		user_id INTO v_content_creator_user_id
	FROM
		content_creators
	WHERE
		id = p_content_creator_id;

	--Eğer kullanıcı id'si bulunamaz ise hata döndür ve işlemi yapma.
	IF v_content_creator_user_id IS NULL THEN	
		RAISE EXCEPTION 'İçerik üreticisinin (ID: %) kullanıcı idsi bulunamadı', p_content_creator_id;
	END IF;

	--gelen içeriğin resimini media tablosuna kaydedelim ve id'sini alalım
	INSERT INTO
		media(
			user_id,
			media_url,
			media_type,
			media_size
		)
	VALUES (
		v_content_creator_user_id,
		p_image_url,
		'PAINTING_IMAGE',
		p_images_size || 'KB'
	)
	RETURNING id INTO v_created_image_id;

	--ana içerik tablosuna içeriğin bilgilerini girelim ve içerik id'sini yakalayalım
	INSERT INTO
		contents(
			content_creator_id,
			title,
			price
		)
	VALUES (
		p_content_creator_id,
		p_title,
		p_price
	)
	RETURNING id INTO v_created_content_id;

	--Şimdi painting_contents tablosuna asıl bilgileri girelim
	INSERT INTO
		painting_contents(
			content_id,
			image_id,
			style,
			description
		)
	VALUES (
		v_created_content_id,
		v_created_image_id,
		p_style,
		p_description
	);

	--Bu işlem yapıldığında otomatik olarak trigger yardımı ile loglanacaktır.

	RAISE NOTICE '% başlığıyla bir resim içeriği eklendi.', p_title;
	
	EXCEPTION
		WHEN OTHERS	THEN
			RAISE EXCEPTION 'içerik eklenirken bir hata oluştu ve işlem yapılmadı, hata: %', SQLERRM;
END;
$$;

--Prosedürün amacı text_based_contents_tablosuna içerik eklemektir
CREATE OR REPLACE PROCEDURE
	add_text_based_content(
		p_title TEXT,
		p_price DECIMAL(10, 2),
		p_content_creator_id UUID,
		p_text TEXT
	)
LANGUAGE
	plpgsql
AS $$
--İhtiyacımız olan değişkenler
DECLARE
	v_created_content_id UUID;
	v_content_creator_user_id UUID;
BEGIN
	-- İçerik üreticisinin varlığını ve kullanıcı ID'sini kontrol et
    SELECT 
		user_id INTO v_content_creator_user_id 
    FROM 
		content_creators 
    WHERE 
		id = p_content_creator_id;

	--Kullanıcı yoksa hata fırlat
    IF v_content_creator_user_id IS NULL THEN
        RAISE EXCEPTION 'Belirtilen İçerik Üreticisi (ID: %) bulunamadı.', p_content_creator_id;
    END IF;

	--ana içerik tablosuna içeriğin bilgilerini girelim ve içerik id'sini yakalayalım
	INSERT INTO
		contents(
			content_creator_id,
			title,
			price
		)
	VALUES (
		p_content_creator_id,
		p_title,
		p_price
	)
	RETURNING id INTO v_created_content_id;

	--Şimdi text_based_contents tablosuna içeriği ekleyelim
	INSERT INTO
		text_based_contents(
			content_id,
			text
		)
	VALUES (
		v_created_content_id,
		p_text
	);

	RAISE NOTICE '% başlığıyla bir yazı tabanlı içerik eklendi.', p_title;

	EXCEPTION
		WHEN OTHERS	THEN
			RAISE EXCEPTION 'içerik eklenirken bir hata oluştu ve işlem yapılmadı, hata: %', SQLERRM;
END;
$$;

--Bu prosedürün amacı comic_contents tablosuna içerik eklemektir
CREATE OR REPLACE PROCEDURE
	add_comic_content(
		p_title TEXT,
		p_price DECIMAL(10, 2),
		p_content_creator_id UUID,
		p_page_urls TEXT[]
	)
LANGUAGE
	plpgsql
AS $$
DECLARE
	v_content_creator_user_id UUID;
	v_created_content_id UUID;
	v_created_comic_id UUID;
	v_current_page_url TEXT;
	v_counter INT := 1;
BEGIN
	-- İçerik üreticisinin varlığını ve kullanıcı ID'sini kontrol et
    SELECT 
		user_id INTO v_content_creator_user_id 
    FROM 
		content_creators 
    WHERE 
		id = p_content_creator_id;

	--Kullanıcı yoksa hata fırlat
    IF v_content_creator_user_id IS NULL THEN
        RAISE EXCEPTION 'Belirtilen İçerik Üreticisi (ID: %) bulunamadı.', p_content_creator_id;
    END IF;

	--Ana içerik tablosuna içeriği girelim ve id'sini tutalım
	INSERT INTO
		contents(
			content_creator_id,
			title,
			price
		)
	VALUES (
		p_content_creator_id,
		p_title,
		p_price
	)
	RETURNING id INTO v_created_content_id;

	-- Karikatürün ana bilgisini tabloya girelim
    INSERT INTO 
		comic_contents(
			content_id
		)
    VALUES (
		v_created_content_id
	)
    RETURNING id INTO v_created_comic_id;

	FOREACH v_current_page_url IN ARRAY p_page_urls
	LOOP
		INSERT INTO
			comic_pages(
				comic_id,
				page_url,
				page_number
			)
		VALUES (
			v_created_comic_id,
			v_current_page_url,
			v_counter
		);

		v_counter := v_counter +1;
	END LOOP;

	RAISE NOTICE '% başlığıyla % sayfalık karikatür başarıyla eklendi.', p_title, (v_counter - 1);

	EXCEPTION
		WHEN OTHERS	THEN
			RAISE EXCEPTION 'içerik eklenirken bir hata oluştu ve işlem yapılmadı, hata: %', SQLERRM;
END;
$$;