-- 1. AŞAMA: KÖK TABLOLAR (LOOKUP TABLES)
-- Bu tablolar başka yerlere bağımlı değildir.
INSERT INTO system_roles (name, description) VALUES 
('ADMIN', 'Sistem Yöneticisi'),
('USER', 'Standart Kullanıcı'),
('MODERATOR', 'İçerik Denetleyicisi')
ON CONFLICT DO NOTHING;

INSERT INTO content_creator_roles (code, name, description) VALUES
('ILLUSTRATOR', 'Çizer', 'Dijital veya geleneksel çizer'),
('WRITER', 'Yazar', 'Hikaye ve roman yazarı'),
('COMIC_ARTIST', 'Karikatürist', 'Çizgi roman üreticisi')
ON CONFLICT DO NOTHING;

INSERT INTO content_tags (name) VALUES 
('Korku'), ('Bilim Kurgu'), ('Fantastik'), ('Romantik'), ('Absürt'), ('Polisiye'), ('Cyberpunk')
ON CONFLICT DO NOTHING;

INSERT INTO content_creator_tags (name, description) VALUES
('Hızlı Teslimat', 'Siparişleri 24 saatte teslim eder'),
('Detaycı', 'Çizimlerinde aşırı detay kullanır'),
('Minimalist', 'Sade tasarımlar yapar')
ON CONFLICT DO NOTHING;


-- 2. AŞAMA: KULLANICILAR, ÜRETİCİLER VE İÇERİK SİMÜLASYONU

DO $$
DECLARE
    v_user_role_id UUID;
    v_user_id UUID;
    v_creator_id UUID;
    v_content_id UUID;
    i INT;
    j INT;
    v_random_price DECIMAL(10,2);
    v_random_title TEXT;
    v_dummy_text TEXT := 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.';
BEGIN
    -- Standart kullanıcı rolünü alalım
    SELECT id INTO v_user_role_id FROM system_roles WHERE name = 'USER' LIMIT 1;

    -- ==========================================
    -- ADIM 2.1: 50 ADET KULLANICI OLUŞTUR
    -- ==========================================
    FOR i IN 1..50 LOOP
        INSERT INTO users (
            role_id, first_name, last_name, username, email, password, birthday
        ) VALUES (
            v_user_role_id,
            'User' || i,
            'Soyad' || floor(random()*1000)::text,
            'kullanici_' || i || '_' || floor(random()*9999)::text,
            'user' || i || '_' || floor(random()*9999)::text || '@gmail.com',
            'hashed_password_123',
            CURRENT_DATE - (floor(random()*10000)::int || ' days')::interval
        ) RETURNING id INTO v_user_id;

        -- ==========================================
        -- ADIM 2.2: BU KULLANICILARIN %40'INI İÇERİK ÜRETİCİSİ YAP
        -- ==========================================
        IF random() < 0.4 THEN
            INSERT INTO content_creators (user_id, biography)
            VALUES (v_user_id, 'Ben sanata aşık bir üreticiyim. Numara: ' || i)
            RETURNING id INTO v_creator_id;

            -- Üreticiye rastgele bir rol ata (Çizer, Yazar vb.)
            INSERT INTO content_creator_roles_relations (content_creator_role_id, content_creator_id)
            SELECT id, v_creator_id FROM content_creator_roles ORDER BY random() LIMIT 1;

            -- ==========================================
            -- ADIM 2.3: SENİN PROSEDÜRLERİNLE İÇERİK BAS (MASSIVE CONTENT)
            -- Her üretici 1 ila 5 arası içerik üretsin
            -- ==========================================
            FOR j IN 1..(1 + floor(random()*4)::int) LOOP
                v_random_price := (floor(random() * 500) + 10)::decimal(10,2);
                v_random_title := CASE 
                    WHEN random() < 0.3 THEN 'Efsanevi Çizim #' || floor(random()*1000)
                    WHEN random() < 0.6 THEN 'Karanlık Hikayeler Bölüm ' || floor(random()*100)
                    ELSE 'Komik Anlar Serisi ' || floor(random()*500)
                END;

                -- RASTGELE TÜR SEÇİMİ VE PROSEDÜR ÇAĞRISI
                IF random() < 0.33 THEN
                    -- >>> RESİM EKLEME PROSEDÜRÜ <<<
                    CALL add_painting_content(
                        v_random_title,
                        v_random_price,
                        v_creator_id,
                        'https://picsum.photos/seed/' || floor(random()*1000) || '/800/600', -- Rastgele resim URL
                        CASE WHEN random() > 0.5 THEN 'Realistik' ELSE 'Anime' END,
                        (1024 + floor(random()*5000)::bigint), -- Boyut
                        'Bu harika bir resim açıklamasıdır.'
                    );
                
                ELSIF random() < 0.66 THEN
                    -- >>> YAZI EKLEME PROSEDÜRÜ <<<
                    CALL add_text_based_content(
                        'Hikaye: ' || v_random_title,
                        v_random_price,
                        v_creator_id,
                        v_dummy_text || v_dummy_text -- Uzun metin
                    );

                ELSE
                    -- >>> KARİKATÜR EKLEME PROSEDÜRÜ <<<
                    CALL add_comic_content(
                        'Çizgi Roman: ' || v_random_title,
                        v_random_price,
                        v_creator_id,
                        ARRAY[
                            'https://comic.com/p1.jpg',
                            'https://comic.com/p2.jpg',
                            'https://comic.com/p3.jpg'
                        ] -- Sayfa URL Array'i
                    );
                END IF;

            END LOOP; -- İçerik döngüsü bitiş
        END IF; -- Üretici if bitiş
    END LOOP; -- Kullanıcı döngüsü bitiş
END $$;


-- 3. AŞAMA: ETKİLEŞİM SİMÜLASYONU (RAPORLARIN DOLU GÖZÜKMESİ İÇİN)
-- İçerikler oluştu ama "Popüler İçerikler" raporunun çalışması için
-- Yorum, Puan ve Satın Alma verisine ihtiyacımız var.

DO $$
DECLARE
    r_content RECORD;
    r_user RECORD;
    v_counter INT;
BEGIN
    -- Sistemdeki her içerik için rastgele etkileşim oluştur
    FOR r_content IN SELECT id, price FROM contents LOOP
        
        -- 1. Rastgele 0-5 arası YORUM ve PUAN (Rating) ekle
        FOR v_counter IN 1..floor(random()*5)::int LOOP
            -- Rastgele bir kullanıcı seç
            SELECT id INTO r_user FROM users ORDER BY random() LIMIT 1;
            
            -- Puan Ver
            INSERT INTO ratings (content_id, user_id, rating)
            VALUES (r_content.id, r_user.id, (floor(random()*5)+1)::decimal(5,2));

            -- Yorum Yap
            INSERT INTO comments (content_id, user_id, text)
            VALUES (r_content.id, r_user.id, 'Harika bir içerik! Kesinlikle tavsiye ederim.');
            
            -- Satın Al (Raporlarda ciro gözüksün diye)
            INSERT INTO user_bought_content_relations (user_id, content_id)
            VALUES (r_user.id, r_content.id)
            ON CONFLICT DO NOTHING; -- Zaten aldıysa hata verme
            
        END LOOP;
    END LOOP;
END $$;