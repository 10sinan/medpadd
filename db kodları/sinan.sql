--fonksiyon

create function icerik_raporlama(p_icerik_adi varchar)
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
as $$
begin
	return query
	select
		c.id,
		cc.id,
		u.first_name,
		u.last_name,
		c.title,
		c.price,
		count(distinct com.id)::int,
		avg(r.rating)
	from contents c
	join content_creators cc on cc.id = c.content_creator_id
	join users u on u.id = cc.user_id
	left join comments com on com.content_id = c.id
	left join ratings r on r.content_id = c.id
	where c.title ilike '%' || p_icerik_adi || '%'
	group by
		c.id,
		cc.id,
		u.first_name,
		u.last_name,
		c.title,
		c.price;
end;
$$ language plpgsql;

select * 
from icerik_raporlama('ilk icerik');
------------------------------------------------------------------------------------------------
--prosedür

create or replace
procedure katil_uyeligi_olustur(
	p_user_id uuid,
	p_name text,
	p_description text,
	p_price numeric
)
language plpgsql
as $$
declare
	v_creator_id uuid;

begin
-- kullanici content creator mi?
	select
	cc.id
	into
	v_creator_id
from
	content_creators cc
where
	cc.user_id = p_user_id;

if v_creator_id is null then
		raise exception 'sadece icerik ureticileri katil uyeligi olusturabilir';
end if;
-- katil uyeligi ekle
	insert
	into
	join_membership (
		content_creator_id,
		name,
		description,
		price
	)
values (
		v_creator_id,
		p_name,
		p_description,
		p_price
	);
-- log at
	insert
	into
	system_logs (user_id,
	log)
values (
		p_user_id,
		'katil uyeligi olusturuldu: ' || p_name
	);
end;

$$;
