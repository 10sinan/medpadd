CREATE OR REPLACE FUNCTION fn_avg_subscribers_by_content_type()
RETURNS TABLE (
    content_category TEXT,      -- İçerik Kategorisi (Resim, Yazı, Karikatür)
    avg_subscribers NUMERIC,    -- O kategorideki üreticilerin ortalama abone sayısı
    total_creators BIGINT       -- O kategoride üretim yapan kaç kişi var
)
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY
    
    -- 1. RESİM ÇİZENLERİN İSTATİSTİĞİ
    SELECT
        'Resim (Painting)'::TEXT,
        COALESCE(AVG(sub_count), 0),
        COUNT(*)
    FROM (
        -- Bu alt sorgu, resim çizen her bir üreticinin tek tek abone sayısını bulur
        SELECT
            cc.id,
            COUNT(DISTINCT fr.user_id) as sub_count
        FROM
            content_creators cc
        JOIN
            contents c ON c.content_creator_id = cc.id
        JOIN
            painting_contents pc ON pc.content_id = c.id -- Sadece resim çizenleri yakala
        LEFT JOIN
            follow_relations fr ON fr.content_creator_id = cc.id
        GROUP BY
            cc.id
    ) AS painting_stats

    UNION ALL

    -- 2. YAZI (HİKAYE/ŞİİR) YAZANLARIN İSTATİSTİĞİ
    SELECT
        'Yazı (Text Based)'::TEXT,
        COALESCE(AVG(sub_count), 0),
        COUNT(*)
    FROM (
        -- Bu alt sorgu, yazı yazan üreticilerin abone sayısını bulur
        SELECT
            cc.id,
            COUNT(DISTINCT fr.user_id) as sub_count
        FROM
            content_creators cc
        JOIN
            contents c ON c.content_creator_id = cc.id
        JOIN
            text_based_contents tbc ON tbc.content_id = c.id -- Sadece yazı yazanları yakala
        LEFT JOIN
            follow_relations fr ON fr.content_creator_id = cc.id
        GROUP BY
            cc.id
    ) AS text_stats

    UNION ALL

    -- 3. KARİKATÜR ÇİZENLERİN İSTATİSTİĞİ
    SELECT
        'Karikatür (Comic)'::TEXT,
        COALESCE(AVG(sub_count), 0),
        COUNT(*)
    FROM (
        -- Bu alt sorgu, karikatür çizen üreticilerin abone sayısını bulur
        SELECT
            cc.id,
            COUNT(DISTINCT fr.user_id) as sub_count
        FROM
            content_creators cc
        JOIN
            contents c ON c.content_creator_id = cc.id
        JOIN
            comic_contents com ON com.content_id = c.id -- Sadece karikatüristleri yakala
        LEFT JOIN
            follow_relations fr ON fr.content_creator_id = cc.id
        GROUP BY
            cc.id
    ) AS comic_stats;

END;
$$;