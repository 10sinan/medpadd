---bu fonksiyon, verilen içerik adını içeren içeriklerin detaylı raporunu döner.
create or replace function icerik_raporlama(p_icerik_adi varchar)
returns table (
	content_id uuid,
	content_creator_id uuid,
	creator_name text,
	creator_lastname text,
	title text,
	price numeric,
	comment_count int,
	avg_rating numeric
)
language sql
as $$
	select
		c.id as content_id,
		cc.id as content_creator_id,
		u.first_name as creator_name,
		u.last_name as creator_lastname,
		c.title,
		c.price,
		coalesce(com.comment_count, 0) as comment_count,
		r.avg_rating
	from contents c
	join content_creators cc on cc.id = c.content_creator_id
	join users u on u.id = cc.user_id

	left join (
		select
			content_id,
			count(*)::int as comment_count
		from comments
		group by content_id
	) com on com.content_id = c.id

	left join (
		select
			content_id,
			avg(rating) as avg_rating
		from ratings
		group by content_id
	) r on r.content_id = c.id

	where c.title ilike '%' || p_icerik_adi || '%';
$$;



--------------------------------
select *
from icerik_raporlama('ilk icerik');