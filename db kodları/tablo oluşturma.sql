--Sistem rolleri tablosu, kullanıcıların sahip olabilecekleri rollerin tablosudur(ADMIN, MODERATOR, NORMAL vb.)
CREATE TABLE IF NOT EXISTS system_roles (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	name TEXT NOT NULL UNIQUE,
	description TEXT,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--Medya tablosu, kullanılan tüm medyaların url'leri bu tabloda bulunacak
CREATE TABLE IF NOT EXISTS media (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	
	user_id UUID NULL,
	
	media_url TEXT NOT NULL,
	media_type TEXT,
	media_size TEXT,

	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--İçerik üreticilerinin rollerini bulunduğu tablo
CREATE TABLE IF NOT EXISTS content_creator_roles (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	
	code TEXT NOT NULL UNIQUE,
	name TEXT NOT NULL UNIQUE,
	description TEXT,

	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--İçerik üreticilerinin doğrulanma rozetleri
CREATE TABLE IF NOT EXISTS content_creator_verification_badges(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	icon_id UUID NULL,
	
	code TEXT NOT NULL UNIQUE,
	name TEXT NOT NULL UNIQUE,
	color TEXT NOT NULL DEFAULT 'gray'
);

--İçerik üreticilerinin etiketleri
CREATE TABLE IF NOT EXISTS content_creator_tags(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	icon_id UUID NULL,

	name TEXT NOT NULL UNIQUE,
	description TEXT,

	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--İçeriklerin etiketleri
CREATE TABLE IF NOT EXISTS content_tags(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	icon_id UUID null,

	name TEXT NOT NULL UNIQUE
);

--Kullanıcı başarım rozetleri
CREATE TABLE IF NOT EXISTS badges(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	icon_id UUID NULL,

	code TEXT NOT NULL UNIQUE,
	color TEXT NOT NULL DEFAULT 'gray',
	name TEXT NOT NULL UNIQUE,
	description TEXT,

	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--Platform abonelikleri
CREATE TABLE IF NOT EXISTS subscriptions(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	icon_id UUID NULL,

	name TEXT NOT NULL UNIQUE,
	description TEXT,
	privileges JSONB NOT NULL DEFAULT '{}',
	
	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

--Kullanıcı bilgilerinin tablosu
CREATE TABLE IF NOT EXISTS users(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	profile_pic_id UUID NULL,
	role_id UUID,

	first_name TEXT NOT NULL,
	last_name TEXT NOT NULL,
	username TEXT NOT NULL UNIQUE,
	birthday DATE,
	email TEXT NOT NULL UNIQUE,
	password TEXT NOT NULL,

	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT fk_users_system_role FOREIGN KEY (role_id)
	REFERENCES system_roles(id)
);

ALTER TABLE media 
ADD CONSTRAINT fk_media_owner FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL;

--içerik üreticileri tablosu
CREATE TABLE IF NOT EXISTS content_creators(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	user_id UUID,

	biography TEXT,

	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	
	CONSTRAINT fk_creators_user FOREIGN KEY (user_id)
	REFERENCES users(id)
);

--Sistem loglarının tablosu
CREATE TABLE IF NOT EXISTS system_logs(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	user_id UUID,

	log TEXT NOT NULL,

	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT fk_syslogs_user FOREIGN KEY (user_id)
	REFERENCES users(id)
);

--Şikayetlerin tablosu
CREATE TABLE IF NOT EXISTS complaints(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	user_id UUID,

	title TEXT NOT NULL,
	complaint TEXT,

	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT fk_complaints_user FOREIGN KEY (user_id)
	REFERENCES users(id)
);

--ana içerikler tablosu
CREATE TABLE IF NOT EXISTS contents(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	content_creator_id UUID,

	title TEXT NOT NULL,
	price DECIMAL(10, 2) DEFAULT 0.00,

	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT fk_contents_creator FOREIGN KEY (content_creator_id)
	REFERENCES content_creators(id)
);

--İçerik üreticilerinin katıl üyelikleri tablosu
CREATE TABLE IF NOT EXISTS join_membership(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	icon_id UUID NULL,
	content_creator_id UUID,

	name TEXT NOT NULL,
	description TEXT,
	price DECIMAL(10, 2) DEFAULT 0.00,

	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT fk_membership_creator FOREIGN KEY (content_creator_id)
	REFERENCES content_creators(id)
);

--Kullanıcıların içerik üreticilerini takip etmesinin ilişkisinin tablosu
CREATE TABLE IF NOT EXISTS follow_relations(
	user_id UUID NOT NULL,
	content_creator_id UUID NOT NULL,

    PRIMARY KEY (user_id, content_creator_id),

	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_follow_user FOREIGN KEY (user_id) 
	REFERENCES users(id) ON DELETE CASCADE,
        
    CONSTRAINT fk_follow_creator FOREIGN KEY (content_creator_id) 
	REFERENCES content_creators(id) ON DELETE CASCADE
);

--İçerik üreticilerinin rolleri ile aralarındaki ilişkinin tablosu
CREATE TABLE IF NOT EXISTS content_creator_roles_relations(
	content_creator_role_id UUID NOT NULL,
	content_creator_id UUID NOT NULL,

    PRIMARY KEY (content_creator_role_id, content_creator_id),

	CONSTRAINT fk_content_creator_role FOREIGN KEY (content_creator_role_id) 
	REFERENCES content_creator_roles(id) ON DELETE CASCADE,
        
    CONSTRAINT fk_ccr_rel_creator FOREIGN KEY (content_creator_id) 
	REFERENCES content_creators(id) ON DELETE CASCADE
);

--İçerik üreticilerinin doğrulanma rozetleri ile ilişkisinin tablosu
CREATE TABLE IF NOT EXISTS content_creator_verification_relations(
	verification_badge_id UUID NOT NULL,
	content_creator_id UUID NOT NULL,

    PRIMARY KEY (verification_badge_id, content_creator_id),

	CONSTRAINT fk_verification_badge FOREIGN KEY (verification_badge_id) 
	REFERENCES content_creator_verification_badges(id) ON DELETE CASCADE,
        
    CONSTRAINT fk_ccv_rel_creator FOREIGN KEY (content_creator_id) 
	REFERENCES content_creators(id) ON DELETE CASCADE
);

--İçerik üreticilerinin etiketleri ile ilişkisinin tablosu
CREATE TABLE IF NOT EXISTS content_creator_tag_relations(
	tag_id UUID NOT NULL,
	content_creator_id UUID NOT NULL,

    PRIMARY KEY (tag_id, content_creator_id),

	CONSTRAINT fk_content_creator_tag FOREIGN KEY (tag_id) 
	REFERENCES content_creator_tags(id) ON DELETE CASCADE,
        
    CONSTRAINT fk_cct_rel_creator FOREIGN KEY (content_creator_id) 
	REFERENCES content_creators(id) ON DELETE CASCADE
);

--resim içeriklerinin tablosu
CREATE TABLE IF NOT EXISTS painting_contents(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	content_id UUID,
	image_id UUID,

	style TEXT,
	description TEXT,

	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT fk_painting_content FOREIGN KEY (content_id) 
	REFERENCES contents(id) ON DELETE CASCADE
);

--Yazı temelli içeriklerin tablosu
CREATE TABLE IF NOT EXISTS text_based_contents(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	content_id UUID,

	text TEXT,

	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT fk_text_content FOREIGN KEY (content_id) 
	REFERENCES contents(id) ON DELETE CASCADE
);

--Karikatür içeriklerinin tablosu
CREATE TABLE IF NOT EXISTS comic_contents(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	content_id UUID,

	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT fk_comic_content FOREIGN KEY (content_id) 
	REFERENCES contents(id) ON DELETE CASCADE
);

--Karikatür sayfalarının tablosu
CREATE TABLE IF NOT EXISTS comic_pages(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	comic_id UUID,

	page_url TEXT NOT NULL,
	page_number INT,

	CONSTRAINT fk_pages_comic FOREIGN KEY (comic_id) 
	REFERENCES comic_contents(id) ON DELETE CASCADE
);

--içeriklerin değerlendirmelerinin tablosu
CREATE TABLE IF NOT EXISTS ratings(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	content_id UUID,
	user_id UUID,

	rating DECIMAL (5, 2),

	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	
	CONSTRAINT fk_ratings_content FOREIGN KEY (content_id) 
	REFERENCES contents(id) ON DELETE CASCADE,

	CONSTRAINT fk_ratings_user FOREIGN KEY (user_id) 
	REFERENCES users(id) ON DELETE CASCADE
);

--içeriklerin yorumlarının bulunduğu tablo
CREATE TABLE IF NOT EXISTS comments(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	content_id UUID,
	user_id UUID,
	parent_comment_id UUID,

	text TEXT NOT NULL,

	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT fk_comments_content FOREIGN KEY (content_id) 
	REFERENCES contents(id) ON DELETE CASCADE,

	CONSTRAINT fk_comments_user FOREIGN KEY (user_id) 
	REFERENCES users(id) ON DELETE CASCADE,

	CONSTRAINT fk_parent_comment FOREIGN KEY (parent_comment_id) 
	REFERENCES comments(id) ON DELETE CASCADE
);

--İçerik etiketlerinin bulunduğu tablo
CREATE TABLE IF NOT EXISTS content_tag_relations(
	tag_id UUID NOT NULL,
	content_id UUID NOT NULL,

    PRIMARY KEY (tag_id, content_id),

	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	
	CONSTRAINT fk_ct_rel_tag FOREIGN KEY (tag_id) 
	REFERENCES content_tags(id) ON DELETE CASCADE,
        
    CONSTRAINT fk_ct_rel_content FOREIGN KEY (content_id) 
	REFERENCES contents(id) ON DELETE CASCADE
);

--İçeriklerin katıl üyelikleri ile ilişkisinin tablosu
CREATE TABLE IF NOT EXISTS content_join_membership_relations(
	join_membership_id UUID NOT NULL,
	content_id UUID NOT NULL,

    PRIMARY KEY (join_membership_id, content_id),

	CONSTRAINT fk_cjm_rel_membership FOREIGN KEY (join_membership_id) 
	REFERENCES join_membership(id) ON DELETE CASCADE,
        
    CONSTRAINT fk_cjm_rel_content FOREIGN KEY (content_id) 
	REFERENCES contents(id) ON DELETE CASCADE
);

--Kullanıcının aldığı içeriklerin tablosu
CREATE TABLE IF NOT EXISTS user_bought_content_relations(
	user_id UUID NOT NULL,
	content_id UUID NOT NULL,

    PRIMARY KEY (user_id, content_id),

	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	
	CONSTRAINT fk_ubc_rel_user FOREIGN KEY (user_id) 
	REFERENCES users(id) ON DELETE CASCADE,
        
    CONSTRAINT fk_ubc_rel_content FOREIGN KEY (content_id) 
	REFERENCES contents(id) ON DELETE CASCADE
);

--Kullanıcıların aldıkları katıl üyeliklerinin tablosu
CREATE TABLE IF NOT EXISTS user_join_membership_relations(
	user_id UUID NOT NULL,
	join_membership_id UUID NOT NULL,

    PRIMARY KEY (user_id, join_membership_id),

	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	
	CONSTRAINT fk_ujm_rel_user FOREIGN KEY (user_id) 
	REFERENCES users(id) ON DELETE CASCADE,
        
    CONSTRAINT fk_ujm_rel_membership FOREIGN KEY (join_membership_id) 
	REFERENCES join_membership(id) ON DELETE CASCADE
);

--Kullanıcıların kazandıkları rozetlerin tablosu
CREATE TABLE IF NOT EXISTS user_badge_relations(
	user_id UUID NOT NULL,
	badge_id UUID NOT NULL,

    PRIMARY KEY (user_id, badge_id),

	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	
	CONSTRAINT fk_ub_rel_user FOREIGN KEY (user_id) 
	REFERENCES users(id) ON DELETE CASCADE,
        
    CONSTRAINT fk_ubr_rel_badge FOREIGN KEY (badge_id) 
	REFERENCES badges(id) ON DELETE CASCADE
);

--Kullanıcıların aldıkları aboneliklerin tablosu
CREATE TABLE IF NOT EXISTS user_subscription_relations(
	user_id UUID NOT NULL,
	subscription_id UUID NOT NULL,

    PRIMARY KEY (user_id, subscription_id),

	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	
	CONSTRAINT fk_usr_rel_user FOREIGN KEY (user_id) 
	REFERENCES users(id) ON DELETE CASCADE,
        
    CONSTRAINT fk_subscription FOREIGN KEY (subscription_id) 
	REFERENCES subscriptions(id) ON DELETE CASCADE
);
------------------------------------
--Tabloları media tablosuna bağlamak
------------------------------------
-- Users tablosundaki profile_pic_id sütununu media tablosuna bağlar
ALTER TABLE users 
ADD CONSTRAINT fk_user_profile_pic 
FOREIGN KEY (profile_pic_id) REFERENCES media(id) ON DELETE SET NULL;

-- İçerik Üreticisi Doğrulanma Rozetleri (Verification Badges)
ALTER TABLE content_creator_verification_badges 
ADD CONSTRAINT fk_verification_badge_icon 
FOREIGN KEY (icon_id) REFERENCES media(id) ON DELETE SET NULL;

-- İçerik Üreticisi Etiketleri
ALTER TABLE content_creator_tags 
ADD CONSTRAINT fk_creator_tag_icon 
FOREIGN KEY (icon_id) REFERENCES media(id) ON DELETE SET NULL;

-- Genel İçerik Etiketleri (Content Tags)
ALTER TABLE content_tags 
ADD CONSTRAINT fk_content_tag_icon 
FOREIGN KEY (icon_id) REFERENCES media(id) ON DELETE SET NULL;

-- Kullanıcı Başarım Rozetleri (Badges)
ALTER TABLE badges 
ADD CONSTRAINT fk_badge_icon 
FOREIGN KEY (icon_id) REFERENCES media(id) ON DELETE SET NULL;

-- Platform Abonelik Planları (Subscriptions)
ALTER TABLE subscriptions 
ADD CONSTRAINT fk_subscription_icon 
FOREIGN KEY (icon_id) REFERENCES media(id) ON DELETE SET NULL;

-- Katıl Üyeliği İkonları (Join Membership)
ALTER TABLE join_membership 
ADD CONSTRAINT fk_membership_icon 
FOREIGN KEY (icon_id) REFERENCES media(id) ON DELETE SET NULL;

-- Bir resim içeriğinin asıl dosyasını media tablosuna bağlar
ALTER TABLE painting_contents 
ADD CONSTRAINT fk_painting_image 
FOREIGN KEY (image_id) REFERENCES media(id) ON DELETE CASCADE;


--------------------------------------------------------
--Tabloların updated_at sütunlarını otomatik güncellemek
--------------------------------------------------------
-- güncelleyici fonksiyon
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

--tablolara güncelleyici trigger'ı eklemek.
CREATE TRIGGER trg_update_users_modtime
    BEFORE UPDATE ON users
    FOR EACH ROW EXECUTE PROCEDURE update_updated_at_column();

-- İçerik Üreticileri
CREATE TRIGGER trg_update_content_creators_modtime
    BEFORE UPDATE ON content_creators
    FOR EACH ROW EXECUTE PROCEDURE update_updated_at_column();

-- Ana İçerikler (Contents)
CREATE TRIGGER trg_update_contents_modtime
    BEFORE UPDATE ON contents
    FOR EACH ROW EXECUTE PROCEDURE update_updated_at_column();

-- Resim İçerikleri
CREATE TRIGGER trg_update_painting_contents_modtime
    BEFORE UPDATE ON painting_contents
    FOR EACH ROW EXECUTE PROCEDURE update_updated_at_column();

-- Yazı İçerikleri
CREATE TRIGGER trg_update_text_based_contents_modtime
    BEFORE UPDATE ON text_based_contents
    FOR EACH ROW EXECUTE PROCEDURE update_updated_at_column();

-- Karikatür İçerikleri
CREATE TRIGGER trg_update_comic_contents_modtime
    BEFORE UPDATE ON comic_contents
    FOR EACH ROW EXECUTE PROCEDURE update_updated_at_column();

-- Yorumlar
CREATE TRIGGER trg_update_comments_modtime
    BEFORE UPDATE ON comments
    FOR EACH ROW EXECUTE PROCEDURE update_updated_at_column();

-- Değerlendirmeler (Ratings)
CREATE TRIGGER trg_update_ratings_modtime
    BEFORE UPDATE ON ratings
    FOR EACH ROW EXECUTE PROCEDURE update_updated_at_column();

-- Abonelikler ve Üyelikler
CREATE TRIGGER trg_update_subscriptions_modtime
    BEFORE UPDATE ON subscriptions
    FOR EACH ROW EXECUTE PROCEDURE update_updated_at_column();

CREATE TRIGGER trg_update_join_membership_modtime
    BEFORE UPDATE ON join_membership
    FOR EACH ROW EXECUTE PROCEDURE update_updated_at_column();

-- Roller ve Rozetler
CREATE TRIGGER trg_update_content_creator_roles_modtime
    BEFORE UPDATE ON content_creator_roles
    FOR EACH ROW EXECUTE PROCEDURE update_updated_at_column();

CREATE TRIGGER trg_update_badges_modtime
    BEFORE UPDATE ON badges
    FOR EACH ROW EXECUTE PROCEDURE update_updated_at_column();