
----bu procedure icerik ureticilerinin katil uyeligi olusturmasini saglar.

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
end;

$$;
