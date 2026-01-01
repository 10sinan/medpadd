
--bu trigger icerik tablosunda insert, update veya delete islemi yapildiginda
--audit_logs tablosuna ilgili islemi loglar

--trigger
create or replace function trg_icerik_loglama()
returns trigger
language plpgsql
as $$
declare
	v_user_id uuid;
begin
	if tg_op = 'DELETE' then
		select user_id
		into v_user_id
		from content_creators
		where id = old.content_creator_id;
	else
		select user_id
		into v_user_id
		from content_creators
		where id = new.content_creator_id;
	end if;

	insert into audit_logs (
		user_id,
		actionn,
		target_table,
		target_id,
		old_values,
		new_values
	)
	values (
		v_user_id,
		tg_op,
		tg_table_name,
		coalesce(new.id, old.id),
		case when tg_op in ('UPDATE','DELETE') then to_jsonb(old) end,
		case when tg_op in ('INSERT','UPDATE') then to_jsonb(new) end
	);

	-- AFTER trigger olduğu için
	return null;
end;
$$;


create trigger icerik_logla
after insert or update or delete
on contents
for each row
execute function trg_icerik_loglama();


--test---------------------------
select actionn, target_table, old_values, new_values
from audit_logs

------------------------------------------------------------------------------------------------
create or replace function trg_yorum_guncelle()
returns trigger
language plpgsql
as $$
declare
	userr_id uuid;--yorum yapan kullanicinin id'si
begin
	if tg_op = 'DELETE' then--yorum yapan kullanicinin id'si silinmeden once al
		userr_id := old.user_id;
	else
		userr_id := new.user_id;
	end if;

	insert into audit_logs (--loglama islemi
		user_id,
		actionn,
		target_table,
		target_id,
		old_values,
		new_values
	)
	values (
		v_user_id,
		tg_op,
		tg_table_name,
		coalesce(new.id, old.id),
		case when tg_op in ('UPDATE','DELETE') then to_jsonb(old) end,
		case when tg_op in ('INSERT','UPDATE') then to_jsonb(new) end
	);

	return null;
end;
$$;


-----------------------
create trigger yorum_guncelle
after insert or update or delete
on comments
for each row
execute function trg_yorum_guncelle();

------------------------------------------------------------------------------------------------
create or replace function trg_icerikUrt_logla()
returns trigger
language plpgsql
as $$
declare
	userr_id uuid;
begin
	-- content_creator silinmeden önce user_id'yi al
	if tg_op = 'DELETE' then
		userr_id := old.user_id;
	else
		userr_id := new.user_id;
	end if;

	insert into audit_logs (
		user_id,
		actionn,
		target_table,
		target_id,
		old_values,
		new_values
	)
	values (
		v_user_id,
		tg_op,
		tg_table_name,
		coalesce(new.id, old.id),
		case when tg_op in ('UPDATE','DELETE') then to_jsonb(old) end,
		case when tg_op in ('INSERT','UPDATE') then to_jsonb(new) end
	);

	return null;
end;
$$;


 

create trigger icerikUrt_logla
after insert or update or delete
on content_creators
for each row
execute function trg_icerikUrt_logla();
