-- ------------------------------------------------------------------
-- TABLE: system_roles
-- İlişkiler: users.role_id -> system_roles.id
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS system_roles (
  id uuid PRIMARY KEY,
  name text NOT NULL UNIQUE,
  description text,
  created_at timestamp without time zone NOT NULL DEFAULT now()
);

-- ------------------------------------------------------------------
-- TABLE: users
-- İlişkiler:
--   users.role_id -> system_roles.id
--   users.profile_pic_id -> media.id  (Bu FK döngüsel olduğu için ALTER TABLE ile eklenecek)
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS users (
  id uuid PRIMARY KEY,
  first_name text NOT NULL,
  last_name text NOT NULL,
  username text NOT NULL UNIQUE,
  birthday date,
  profile_pic_id uuid, -- FK eklenecek ALTER ile (media tablosu oluşturulduktan sonra)
  role_id uuid NOT NULL,
  email text NOT NULL UNIQUE,
  password text NOT NULL,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  updated_at timestamp without time zone
  -- FOREIGN KEY (role_id) added inline below
);

-- role_id foreign key (users -> system_roles)
ALTER TABLE users
  ADD CONSTRAINT fk_users_role
  FOREIGN KEY (role_id) REFERENCES system_roles(id) ON DELETE RESTRICT ON UPDATE CASCADE;

-- ------------------------------------------------------------------
-- TABLE: content_creators
-- İlişkiler: content_creators.user_id -> users.id
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS content_creators (
  id uuid PRIMARY KEY,
  user_id uuid NOT NULL,
  biography text,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  updated_at timestamp without time zone,
  CONSTRAINT fk_content_creators_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- ------------------------------------------------------------------
-- TABLE: media
-- İlişkiler:
--   media.user_id -> users.id
--   media.id referenced by many icon_id or image_id columns elsewhere
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS media (
  id uuid PRIMARY KEY,
  user_id uuid, -- optional owner
  media_type text,
  media_url text NOT NULL,
  media_size text,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT fk_media_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);

-- ------------------------------------------------------------------
-- Now add the previously deferred FK: users.profile_pic_id -> media.id
-- (deferred because media and users have cyclic dependency)
-- ------------------------------------------------------------------
ALTER TABLE users
  ADD CONSTRAINT fk_users_profile_pic
  FOREIGN KEY (profile_pic_id) REFERENCES media(id) ON DELETE SET NULL;

-- ------------------------------------------------------------------
-- TABLE: content_creator_tags
-- İlişkiler: content_creator_tags.icon_id -> media.id
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS content_creator_tags (
  id uuid PRIMARY KEY,
  icon_id uuid,
  name text NOT NULL,
  description text,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  updated_at timestamp without time zone,
  CONSTRAINT fk_content_creator_tags_icon FOREIGN KEY (icon_id) REFERENCES media(id) ON DELETE SET NULL
);

-- ------------------------------------------------------------------
-- TABLE: content_tags
-- İlişkiler: content_tags.icon_id -> media.id
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS content_tags (
  id uuid PRIMARY KEY,
  name text NOT NULL,
  icon_id uuid,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT fk_content_tags_icon FOREIGN KEY (icon_id) REFERENCES media(id) ON DELETE SET NULL
);

-- ------------------------------------------------------------------
-- TABLE: subscriptions
-- İlişkiler: subscriptions.icon_id -> media.id
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS subscriptions (
  id uuid PRIMARY KEY,
  icon_id uuid,
  name text NOT NULL,
  description text,
  privileges jsonb,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  updated_at timestamp without time zone,
  CONSTRAINT fk_subscriptions_icon FOREIGN KEY (icon_id) REFERENCES media(id) ON DELETE SET NULL
);

-- ------------------------------------------------------------------
-- TABLE: badges
-- İlişkiler: badges.icon_id -> media.id
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS badges (
  id uuid PRIMARY KEY,
  icon_id uuid,
  color text,
  code text,
  name text NOT NULL,
  description text,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  updated_at timestamp without time zone,
  CONSTRAINT fk_badges_icon FOREIGN KEY (icon_id) REFERENCES media(id) ON DELETE SET NULL
);

-- ------------------------------------------------------------------
-- TABLE: content_creator_verifications
-- İlişkiler: content_creator_verifications.icon_id -> media.id
-- (NOT linked to content_creators by FK in user's sheet; kept as-is)
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS content_creator_verifications (
  id uuid PRIMARY KEY,
  icon_id uuid,
  code text,
  name text,
  color text,
  CONSTRAINT fk_ccv_icon FOREIGN KEY (icon_id) REFERENCES media(id) ON DELETE SET NULL
);

-- ------------------------------------------------------------------
-- TABLE: content_creator_roles
-- İlişkiler: içerik üretici rollerini tanımlar, content_creator_roles_relations ile ilişkilenecek
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS content_creator_roles (
  id uuid PRIMARY KEY,
  name text NOT NULL,
  code text,
  description text,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  updated_at timestamp without time zone
);

-- ------------------------------------------------------------------
-- TABLE: contents
-- İlişkiler: contents.content_creator_id -> content_creators.id
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS contents (
  id uuid PRIMARY KEY,
  content_creator_id uuid NOT NULL,
  title text NOT NULL,
  price double precision,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  updated_at timestamp without time zone,
  CONSTRAINT fk_contents_creator FOREIGN KEY (content_creator_id) REFERENCES content_creators(id) ON DELETE CASCADE
);

-- ------------------------------------------------------------------
-- TABLE: story_contents
-- İlişkiler: story_contents.content_id -> contents.id
--             story_contents.media_id -> media.id
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS story_contents (
  id uuid PRIMARY KEY,
  content_id uuid NOT NULL,
  text text,
  media_id uuid,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  updated_at timestamp without time zone,
  CONSTRAINT fk_story_contents_content FOREIGN KEY (content_id) REFERENCES contents(id) ON DELETE CASCADE,
  CONSTRAINT fk_story_contents_media FOREIGN KEY (media_id) REFERENCES media(id) ON DELETE SET NULL
);

-- ------------------------------------------------------------------
-- TABLE: painting_contents
-- İlişkiler: painting_contents.content_id -> contents.id
--             painting_contents.image_id -> media.id
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS painting_contents (
  id uuid PRIMARY KEY,
  content_id uuid NOT NULL,
  image_id uuid,
  style text,
  description text,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  updated_at timestamp without time zone,
  CONSTRAINT fk_painting_contents_content FOREIGN KEY (content_id) REFERENCES contents(id) ON DELETE CASCADE,
  CONSTRAINT fk_painting_contents_image FOREIGN KEY (image_id) REFERENCES media(id) ON DELETE SET NULL
);

-- ------------------------------------------------------------------
-- TABLE: poetry_contents
-- İlişkiler: poetry_contents.content_id -> contents.id
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS poetry_contents (
  id uuid PRIMARY KEY,
  content_id uuid NOT NULL,
  text text,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  updated_at timestamp without time zone,
  CONSTRAINT fk_poetry_contents_content FOREIGN KEY (content_id) REFERENCES contents(id) ON DELETE CASCADE
);

-- ------------------------------------------------------------------
-- TABLE: comic_contents
-- İlişkiler: comic_contents.content_id -> contents.id
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS comic_contents (
  id uuid PRIMARY KEY,
  content_id uuid NOT NULL,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  updated_at timestamp without time zone,
  CONSTRAINT fk_comic_contents_content FOREIGN KEY (content_id) REFERENCES contents(id) ON DELETE CASCADE
);

-- ------------------------------------------------------------------
-- TABLE: comic_pages
-- İlişkiler: comic_pages.comic_id -> comic_contents.id
-- NOT: sütun adı "page number" orijinaldeydi; SQL'de boşluk izinli olması için çift tırnak kullanıldı.
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS comic_pages (
  id uuid PRIMARY KEY,
  comic_id uuid NOT NULL,
  page_url text,
  "page number" integer,
  CONSTRAINT fk_comic_pages_comic FOREIGN KEY (comic_id) REFERENCES comic_contents(id) ON DELETE CASCADE
);

-- ------------------------------------------------------------------
-- TABLE: ratings
-- İlişkiler: ratings.user_id -> users.id
--            ratings.content_id -> contents.id
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS ratings (
  id uuid PRIMARY KEY,
  user_id uuid NOT NULL,
  content_id uuid NOT NULL,
  rating double precision NOT NULL,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  updated_at timestamp without time zone,
  CONSTRAINT fk_ratings_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  CONSTRAINT fk_ratings_content FOREIGN KEY (content_id) REFERENCES contents(id) ON DELETE CASCADE
);

-- ------------------------------------------------------------------
-- TABLE: comments
-- İlişkiler: comments.user_id -> users.id
--            comments.content_id -> contents.id
--            comments.parent_comment -> comments.id (self reference)
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS comments (
  id uuid PRIMARY KEY,
  user_id uuid NOT NULL,
  content_id uuid NOT NULL,
  parent_comment uuid,
  text text NOT NULL,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  updated_at timestamp without time zone,
  CONSTRAINT fk_comments_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  CONSTRAINT fk_comments_content FOREIGN KEY (content_id) REFERENCES contents(id) ON DELETE CASCADE,
  CONSTRAINT fk_comments_parent FOREIGN KEY (parent_comment) REFERENCES comments(id) ON DELETE CASCADE
);

-- ------------------------------------------------------------------
-- TABLE: complaints
-- İlişkiler:
--   complaints.user_id -> users.id
--   complaints.media_id -> media.id
--   complaints.target_id : polymorphic target (no FK) ; target_type string describes target table
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS complaints (
  id uuid PRIMARY KEY,
  user_id uuid NOT NULL,
  media_id uuid,
  target_id uuid,
  target_type text,
  title text,
  complaint text,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  updated_at timestamp without time zone,
  CONSTRAINT fk_complaints_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  CONSTRAINT fk_complaints_media FOREIGN KEY (media_id) REFERENCES media(id) ON DELETE SET NULL
);

-- ------------------------------------------------------------------
-- TABLE: join_memberships
-- İlişkiler: join_memberships.content_creator_id -> content_creators.id
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS join_memberships (
  id uuid PRIMARY KEY,
  content_creator_id uuid NOT NULL,
  icon_id uuid,
  name text NOT NULL,
  description text,
  price double precision,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  updated_at timestamp without time zone,
  CONSTRAINT fk_join_memberships_creator FOREIGN KEY (content_creator_id) REFERENCES content_creators(id) ON DELETE CASCADE,
  CONSTRAINT fk_join_memberships_icon FOREIGN KEY (icon_id) REFERENCES media(id) ON DELETE SET NULL
);

-- ------------------------------------------------------------------
-- TABLE: user_joing_membership_relations
-- İlişkiler: user_joing_membership_relations.join_membership_id -> join_memberships.id
--            user_joing_membership_relations.user_id -> users.id
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS user_joing_membership_relations (
  id uuid PRIMARY KEY,
  join_membership_id uuid NOT NULL,
  user_id uuid NOT NULL,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT fk_user_joinmem_join FOREIGN KEY (join_membership_id) REFERENCES join_memberships(id) ON DELETE CASCADE,
  CONSTRAINT fk_user_joinmem_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- ------------------------------------------------------------------
-- TABLE: content_creator_tag_relations
-- İlişkiler: content_creator_tag_relations.content_creator_id -> content_creators.id
--            content_creator_tag_relations.tag_id -> content_creator_tags.id
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS content_creator_tag_relations (
  id uuid PRIMARY KEY,
  content_creator_id uuid NOT NULL,
  tag_id uuid NOT NULL,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT fk_cctr_creator FOREIGN KEY (content_creator_id) REFERENCES content_creators(id) ON DELETE CASCADE,
  CONSTRAINT fk_cctr_tag FOREIGN KEY (tag_id) REFERENCES content_creator_tags(id) ON DELETE CASCADE
);

-- ------------------------------------------------------------------
-- TABLE: content_creator_roles_relations
-- İlişkiler: content_creator_roles_relations.content_creator_id -> content_creators.id
--            content_creator_roles_relations.role_id -> content_creator_roles.id
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS content_creator_roles_relations (
  id uuid PRIMARY KEY,
  content_creator_id uuid NOT NULL,
  role_id uuid NOT NULL,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT fk_ccrr_creator FOREIGN KEY (content_creator_id) REFERENCES content_creators(id) ON DELETE CASCADE,
  CONSTRAINT fk_ccrr_role FOREIGN KEY (role_id) REFERENCES content_creator_roles(id) ON DELETE CASCADE
);

-- ------------------------------------------------------------------
-- TABLE: content_tag_relations
-- İlişkiler: content_tag_relations.content_id -> contents.id
--            content_tag_relations.tag_id -> content_tags.id
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS content_tag_relations (
  id uuid PRIMARY KEY,
  content_id uuid NOT NULL,
  tag_id uuid NOT NULL,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT fk_ctr_content FOREIGN KEY (content_id) REFERENCES contents(id) ON DELETE CASCADE,
  CONSTRAINT fk_ctr_tag FOREIGN KEY (tag_id) REFERENCES content_tags(id) ON DELETE CASCADE
);

-- ------------------------------------------------------------------
-- TABLE: user_badge_relation
-- İlişkiler: user_badge_relation.user_id -> users.id
--            user_badge_relation.badge_id -> badges.id
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS user_badge_relation (
  id uuid PRIMARY KEY,
  user_id uuid NOT NULL,
  badge_id uuid NOT NULL,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT fk_ubr_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  CONSTRAINT fk_ubr_badge FOREIGN KEY (badge_id) REFERENCES badges(id) ON DELETE CASCADE
);

-- ------------------------------------------------------------------
-- TABLE: user_subscription_relations
-- İlişkiler: user_subscription_relations.user_id -> users.id
--            user_subscription_relations.subscription_id -> subscriptions.id
-- ------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS user_subscription_relations (
  id uuid PRIMARY KEY,
  user_id uuid NOT NULL,
  subscription_id uuid NOT NULL,
  created_at timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT fk_usr_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  CONSTRAINT fk_usr_subscription FOREIGN KEY (subscription_id) REFERENCES subscriptions(id) ON DELETE CASCADE
);
