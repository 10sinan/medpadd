-------------------------
--RAPORLAMA FONKSİYONLARI
-------------------------

--Belirli tarihler arasındaki en popüler içerikleri popülerliğe göre listeleyen fonksiyon
CREATE OR REPLACE FUNCTION
	fn_get_popular_contents_report(
		p_start_date TIMESTAMP WITH TIME ZONE,
		p_end_date TIMESTAMP WITH TIME ZONE
	)
RETURNS TABLE(
	content_id UUID,
	content_title TEXT,
	creator_username TEXT,
	popularity_score DECIMAL(10, 2)
)
AS $$
BEGIN
	RETURN QUERY
	SELECT
		c.id,
		c.title,
		u.username,
		(
			--RATING * 5 + YORUM_SAYISI
			COALESCE(AVG(r.rating), 0) * 5 + COUNT(com.id)
		) AS score
	FROM
		contents c
	JOIN
		content_creators cc ON cc.id = c.content_creator_id
	JOIN
		users u ON u.id = cc.user_id
	LEFT JOIN
		ratings r ON r.content_id = c.id
	LEFT JOIN
		comments com ON com.content_id = c.id
	WHERE
		c.created_at BETWEEN p_start_date AND p_end_date
	GROUP BY
		c.id,
		c.title,
		u.username
	ORDER BY
		score DESC;
END;
$$ LANGUAGE plpgsql;

--İçerik türlerine göre statlar
CREATE OR REPLACE FUNCTION
	fn_content_type_performance()
RETURNS TABLE(
	content_type TEXT,
	content_count BIGINT,
	total_revenue DECIMAL(20, 2)
)
AS $$
BEGIN
	RETURN QUERY
		SELECT
			'Resim',
			COUNT(*),
			COALESCE(SUM(c.price), 0)
		FROM
			painting_contents p
		JOIN
			contents c ON c.id = p.content_id
	UNION ALL
		SELECT
			'Yazı tabanlı',
			COUNT(*),
			COALESCE(SUM(c.price), 0)
		FROM
			text_based_contents t
		JOIN
			contents c ON c.id = t.content_id
	UNION ALL
		SELECT
			'Karikatür',
			COUNT(*),
			COALESCE(SUM(c.price), 0)
		FROM
			comic_contents cc
		JOIN
			contents c ON c.id = cc.content_id;
END;
$$ LANGUAGE plpgsql;

--Kullanıcı harcamaları raporu
CREATE OR REPLACE FUNCTION
	fn_user_spending_report(
		p_limit INT DEFAULT 10
	)
RETURNS TABLE (
	user_id UUID,
	user_fullname TEXT,
	username TEXT,
	total_bought_content_count BIGINT,
	total_expense DECIMAL(20, 2)
)
AS $$
BEGIN
	RETURN QUERY
	SELECT
		u.id,
		u.first_name || ' ' || u.last_name,
		u.username,
		COUNT(ub.content_id),
		COALESCE(SUM(c.price), 0)
	FROM
		users u
	JOIN
		user_bought_content_relations ub ON u.id = ub.user_id
	JOIN
		contents c ON c.id = ub.content_id
	GROUP BY
		u.id,
		u.username,
		u.first_name,
		u.last_name
	ORDER BY
		total_expense DESC
	LIMIT 
		p_limit;
END;
$$ LANGUAGE plpgsql;

--belirli bir tablonun loglarını analiz etmek için gerekli fonksiyon
CREATE OR REPLACE FUNCTION
	fn_system_audit_summary(
		p_table_name TEXT
	)
RETURNS TABLE (
	action_type TEXT,
	total_action BIGINT,
	last_action_date TIMESTAMP WITH TIME ZONE
) AS $$
BEGIN
	RETURN QUERY
	SELECT
		action,
		COUNT(*),
		MAX(created_at)
	FROM
		audit_logs
	WHERE
		target_table = p_table_name
	GROUP BY
		action;
END;
$$ LANGUAGE plpgsql;