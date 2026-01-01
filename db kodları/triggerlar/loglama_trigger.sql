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
		action,
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
select action, target_table, old_values, new_values
from audit_logs;

------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION trg_yorum_guncelle()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
DECLARE
    v_user_id UUID;
BEGIN
    -- İşlem türüne göre user_id'yi al
    IF TG_OP = 'DELETE' THEN
        v_user_id := OLD.user_id;
    ELSE
        v_user_id := NEW.user_id;
    END IF;

    -- Audit Logs tablosuna ekle
    INSERT INTO audit_logs (
        user_id,
        action,
        target_table,
        target_id,
        old_values,
        new_values
    )
    VALUES (
        v_user_id,
        TG_OP,
        TG_TABLE_NAME,
        COALESCE(NEW.id, OLD.id),
        CASE WHEN TG_OP IN ('UPDATE','DELETE') THEN to_jsonb(OLD) END,
        CASE WHEN TG_OP IN ('INSERT','UPDATE') THEN to_jsonb(NEW) END
    );

    RETURN NULL;
END;
$$;


-----------------------
create trigger yorum_guncelle
after insert or update or delete
on comments
for each row
execute function trg_yorum_guncelle();

------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION trg_icerikUrt_logla()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
DECLARE
    v_user_id UUID; -- Değişken adı standart: v_user_id
BEGIN
    -- İşlem türüne göre user_id'yi doğru yerden al
    IF TG_OP = 'DELETE' THEN
        v_user_id := OLD.user_id;
    ELSE
        v_user_id := NEW.user_id;
    END IF;

    -- Log tablosuna ekle
    INSERT INTO audit_logs (
        user_id,
        action,
        target_table,
        target_id,
        old_values,
        new_values
    )
    VALUES (
        v_user_id, -- Artık yukarıdaki v_user_id ile eşleşiyor
        TG_OP,
        TG_TABLE_NAME,
        COALESCE(NEW.id, OLD.id),
        CASE WHEN TG_OP IN ('UPDATE','DELETE') THEN to_jsonb(OLD) END,
        CASE WHEN TG_OP IN ('INSERT','UPDATE') THEN to_jsonb(NEW) END
    );

    RETURN NULL;
END;
$$;

create trigger icerikUrt_logla
after insert or update or delete
on content_creators
for each row
execute function trg_icerikUrt_logla();
