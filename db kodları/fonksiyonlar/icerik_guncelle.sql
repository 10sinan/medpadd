CREATE OR REPLACE PROCEDURE update_painting_content_details(
    p_content_id UUID,
    p_title TEXT DEFAULT NULL,
    p_price DECIMAL(10, 2) DEFAULT NULL,
    p_style TEXT DEFAULT NULL,
    p_description TEXT DEFAULT NULL
)
LANGUAGE plpgsql
AS $$
BEGIN
    -- 1. Ana Tabloyu (contents) Güncelle
    -- COALESCE sayesinde eğer p_title NULL gelirse eski değer (title) korunur.
    UPDATE contents
    SET 
        title = COALESCE(p_title, title),
        price = COALESCE(p_price, price)
    WHERE id = p_content_id;

    -- 2. Alt Tabloyu (painting_contents) Güncelle
    UPDATE painting_contents
    SET 
        style = COALESCE(p_style, style),
        description = COALESCE(p_description, description)
    WHERE content_id = p_content_id;

    RAISE NOTICE 'Resim içeriği (ID: %) başarıyla güncellendi.', p_content_id;
END;
$$;
CREATE OR REPLACE PROCEDURE update_text_based_content_details(
    p_content_id UUID,
    p_title TEXT DEFAULT NULL,
    p_price DECIMAL(10, 2) DEFAULT NULL,
    p_text TEXT DEFAULT NULL
)
LANGUAGE plpgsql
AS $$
BEGIN
    -- Ana tablo güncellemesi
    UPDATE contents
    SET 
        title = COALESCE(p_title, title),
        price = COALESCE(p_price, price)
    WHERE id = p_content_id;

    -- Yazı tablosu güncellemesi
    UPDATE text_based_contents
    SET 
        text = COALESCE(p_text, text)
    WHERE content_id = p_content_id;

    RAISE NOTICE 'Yazı içeriği (ID: %) başarıyla güncellendi.', p_content_id;
END;
$$;

CREATE OR REPLACE PROCEDURE update_comic_content_details(
    p_content_id UUID,
    p_title TEXT DEFAULT NULL,
    p_price DECIMAL(10, 2) DEFAULT NULL
)
LANGUAGE plpgsql
AS $$
BEGIN
    -- Sadece ana tabloyu güncellemek yeterli (sayfalar ayrı iş)
    UPDATE contents
    SET 
        title = COALESCE(p_title, title),
        price = COALESCE(p_price, price)
    WHERE id = p_content_id;

    RAISE NOTICE 'Karikatür içeriği (ID: %) ana bilgileri güncellendi.', p_content_id;
END;
$$;

CREATE OR REPLACE PROCEDURE update_any_content_router(
    p_content_id UUID,
    p_title TEXT DEFAULT NULL,
    p_price DECIMAL(10, 2) DEFAULT NULL,
    p_text TEXT DEFAULT NULL,         -- Sadece yazı için kullanılır
    p_style TEXT DEFAULT NULL,        -- Sadece resim için kullanılır
    p_description TEXT DEFAULT NULL   -- Sadece resim için kullanılır
)
LANGUAGE plpgsql
AS $$
DECLARE
    v_is_painting BOOLEAN;
    v_is_text BOOLEAN;
    v_is_comic BOOLEAN;
BEGIN
    -- 1. İçeriğin türünü tespit et
    SELECT EXISTS(SELECT 1 FROM painting_contents WHERE content_id = p_content_id) INTO v_is_painting;
    SELECT EXISTS(SELECT 1 FROM text_based_contents WHERE content_id = p_content_id) INTO v_is_text;
    SELECT EXISTS(SELECT 1 FROM comic_contents WHERE content_id = p_content_id) INTO v_is_comic;

    -- 2. Türüne göre ilgili alt prosedüre yönlendir
    IF v_is_painting THEN
        CALL update_painting_content_details(p_content_id, p_title, p_price, p_style, p_description);
        
    ELSIF v_is_text THEN
        CALL update_text_based_content_details(p_content_id, p_title, p_price, p_text);
        
    ELSIF v_is_comic THEN
        CALL update_comic_content_details(p_content_id, p_title, p_price);
        
    ELSE
        RAISE EXCEPTION 'Verilen ID (ID: %) ile eşleşen bir içerik türü bulunamadı veya ID geçersiz.', p_content_id;
    END IF;

END;
$$;