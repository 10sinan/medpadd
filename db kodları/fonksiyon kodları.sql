/* bu fonksiyon, verilen kullanıcı kimliğine (user_id) göre kullanıcının tam adını döndürür */
CREATE OR REPLACE FUNCTION get_user_full_name(p_user_id uuid) RETURNS text AS $$
DECLARE full_name text;
BEGIN
SELECT first_name || ' ' || last_name INTO full_name
FROM users
WHERE id = p_user_id;
RETURN full_name;
END;
$$ LANGUAGE plpgsql;

/* bu fonksiyon, verilen içerik oluşturucu kimliğine (creator_id) göre o oluşturucunun tüm içeriklerini döndürür */
CREATE OR REPLACE FUNCTION get_contents_by_creator(p_creator_id uuid) RETURNS TABLE(
        content_id uuid,
        title text,
        price double precision,
        created_at timestamp
    ) AS $$ BEGIN RETURN QUERY
SELECT c.id,
    c.title,
    c.price,
    c.created_at
FROM contents c
    JOIN content_creators cc ON cc.id = c.content_creator_id
WHERE cc.id = p_creator_id;
END;
$$ LANGUAGE plpgsql;

/* bu fonksiyon, verilen içerik kimliğine (content_id) göre o içeriğin ortalama puanını döndürür */
CREATE OR REPLACE FUNCTION get_average_rating(p_content_id uuid) RETURNS numeric AS $$
DECLARE avg_rating numeric;
BEGIN
SELECT AVG(rating) INTO avg_rating
FROM ratings
WHERE content_id = p_content_id;
RETURN COALESCE(avg_rating, 0);
END;
$$ LANGUAGE plpgsql;

/* bu fonksiyon, verilen içerik kimliğine (content_id) göre o içeriğe ait etiketleri ve ikon URL'lerini döndürür */
CREATE OR REPLACE FUNCTION get_content_tags(p_content_id uuid)
RETURNS TABLE(tag_name text, icon_url text) AS $$
BEGIN
    RETURN QUERY
    SELECT t.name, m.media_url
    FROM content_tag_relations ctr
    JOIN content_tags t ON t.id = ctr.tag_id
    LEFT JOIN media m ON m.id = t.icon_id
    WHERE ctr.content_id = p_content_id;
END;
$$ LANGUAGE plpgsql;
