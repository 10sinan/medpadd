# API Endpoints (CRUD and Lookups)

Her başlık altında temel CRUD uçları ve ek arama uçları listelenir. Parametre tipleri parantez içinde belirtilmiştir; body alanları ilgili entity JSON karşılığıdır.

## Health

- GET /health-check — servis ayakta mı kontrolü

## Users

- POST /api/users — body: Users
- GET /api/users/{id} — path: id (UUID)
- GET /api/users — tüm kullanıcılar
- DELETE /api/users/{id} — path: id (UUID)
- GET /api/users/by-username/{username} — path: username
- GET /api/users/by-email/{email} — path: email
- GET /api/users/exists/by-username/{username} — path: username, varlık kontrolü
- GET /api/users/exists/by-email/{email} — path: email, varlık kontrolü

## System Roles

- POST /api/system-roles — body: SystemRoles
- GET /api/system-roles/{id} — path: id (UUID)
- GET /api/system-roles — tüm roller
- DELETE /api/system-roles/{id} — path: id (UUID)
- GET /api/system-roles/by-name/{roleName} — path: roleName

## Media

- POST /api/media — body: Media
- GET /api/media/{id} — path: id (UUID)
- GET /api/media — tüm medya
- DELETE /api/media/{id} — path: id (UUID)
- GET /api/media/by-user/{userId} — path: userId (UUID)

## Badges

- POST /api/badges — body: Badges
- GET /api/badges/{id} — path: id (UUID)
- GET /api/badges — tüm rozetler
- DELETE /api/badges/{id} — path: id (UUID)
- GET /api/badges/by-code/{code} — path: code

## User Badge Relations

- POST /api/user-badge-relations — body: UserBadgeRelations
- GET /api/user-badge-relations/{id} — path: id (UUID)
- GET /api/user-badge-relations — tüm ilişkiler
- DELETE /api/user-badge-relations/{id} — path: id (UUID)
- GET /api/user-badge-relations/by-user/{userId} — path: userId (UUID)
- GET /api/user-badge-relations/by-badge/{badgeId} — path: badgeId (UUID)

## Subscriptions

- POST /api/subscriptions — body: Subscriptions
- GET /api/subscriptions/{id} — path: id (UUID)
- GET /api/subscriptions — tüm abonelikler
- DELETE /api/subscriptions/{id} — path: id (UUID)
- GET /api/subscriptions/by-name/{name} — path: name

## User Subscription Relations

- POST /api/user-subscription-relations — body: UserSubscriptionsRelations
- GET /api/user-subscription-relations/{id} — path: id (UUID)
- GET /api/user-subscription-relations — tüm ilişkiler
- DELETE /api/user-subscription-relations/{id} — path: id (UUID)
- GET /api/user-subscription-relations/by-user/{userId} — path: userId (UUID)
- GET /api/user-subscription-relations/by-subscription/{subscriptionId} — path: subscriptionId (UUID)

## Content Creators

- POST /api/content-creators — body: ContentCreators
- GET /api/content-creators/{id} — path: id (UUID)
- GET /api/content-creators — tüm içerik üreticileri
- DELETE /api/content-creators/{id} — path: id (UUID)
- GET /api/content-creators/by-user/{userId} — path: userId (UUID)

## Content Creator Roles

- POST /api/content-creator-roles — body: ContentCreatorRoles
- GET /api/content-creator-roles/{id} — path: id (UUID)
- GET /api/content-creator-roles — tüm roller
- DELETE /api/content-creator-roles/{id} — path: id (UUID)
- GET /api/content-creator-roles/by-code/{code} — path: code

## Content Creator Roles Relations

- POST /api/content-creator-roles-relations — body: ContentCreatorRolesRelations
- GET /api/content-creator-roles-relations/{id} — path: id (UUID)
- GET /api/content-creator-roles-relations — tüm ilişkiler
- DELETE /api/content-creator-roles-relations/{id} — path: id (UUID)
- GET /api/content-creator-roles-relations/by-content-creator/{contentCreatorId} — path: contentCreatorId (UUID)
- GET /api/content-creator-roles-relations/by-role/{roleId} — path: roleId (UUID)

## Content Creator Tags

- POST /api/content-creator-tags — body: ContentCreatorTags
- GET /api/content-creator-tags/{id} — path: id (UUID)
- GET /api/content-creator-tags — tüm etiketler
- DELETE /api/content-creator-tags/{id} — path: id (UUID)
- GET /api/content-creator-tags/by-name/{name} — path: name

## Content Creator Tags Relations

- POST /api/content-creator-tags-relations — body: ContentCreatorTagsRelations
- GET /api/content-creator-tags-relations/{id} — path: id (UUID)
- GET /api/content-creator-tags-relations — tüm ilişkiler
- DELETE /api/content-creator-tags-relations/{id} — path: id (UUID)
- GET /api/content-creator-tags-relations/by-content-creator/{contentCreatorId} — path: contentCreatorId (UUID)
- GET /api/content-creator-tags-relations/by-tag/{tagId} — path: tagId (UUID)

## Content Creator Verifications Badges

- POST /api/content-creator-verifications-badges — body: ContentCreatorVerificationsBadges
- GET /api/content-creator-verifications-badges/{id} — path: id (UUID)
- GET /api/content-creator-verifications-badges — tüm doğrulama rozetleri
- DELETE /api/content-creator-verifications-badges/{id} — path: id (UUID)
- GET /api/content-creator-verifications-badges/by-code/{code} — path: code

## Follow Relations

- POST /api/follow-relations — body: FollowRelations
- GET /api/follow-relations/{id} — path: id (UUID)
- GET /api/follow-relations — tüm takip ilişkileri
- DELETE /api/follow-relations/{id} — path: id (UUID)
- GET /api/follow-relations/by-content-creator/{contentCreatorId} — path: contentCreatorId (UUID)
- GET /api/follow-relations/by-role/{roleId} — path: roleId (UUID)

## Join Memberships

- POST /api/join-memberships — body: JoinMemberships
- GET /api/join-memberships/{id} — path: id (UUID)
- GET /api/join-memberships — tüm üyelik katılımları
- DELETE /api/join-memberships/{id} — path: id (UUID)
- GET /api/join-memberships/by-content-creator/{contentCreatorId} — path: contentCreatorId (UUID)

## User Join Membership Relations

- POST /api/user-join-membership-relations — body: UserJoinMembershipRelations
- GET /api/user-join-membership-relations/{id} — path: id (UUID)
- GET /api/user-join-membership-relations — tüm ilişkiler
- DELETE /api/user-join-membership-relations/{id} — path: id (UUID)
- GET /api/user-join-membership-relations/by-user/{userId} — path: userId (UUID)
- GET /api/user-join-membership-relations/by-join-membership/{joinMembershipId} — path: joinMembershipId (UUID)

## Content

- POST /api/contents — body: Content
- GET /api/contents/{id} — path: id (UUID)
- GET /api/contents — tüm içerikler
- DELETE /api/contents/{id} — path: id (UUID)
- GET /api/contents/by-content-creator/{contentCreatorId} — path: contentCreatorId (UUID)

## Content Tags

- POST /api/content-tags — body: ContentTags
- GET /api/content-tags/{id} — path: id (UUID)
- GET /api/content-tags — tüm etiketler
- DELETE /api/content-tags/{id} — path: id (UUID)

## Content Tag Relations

- POST /api/content-tag-relations — body: ContentTagRelations
- GET /api/content-tag-relations/{id} — path: id (UUID)
- GET /api/content-tag-relations — tüm ilişkiler
- DELETE /api/content-tag-relations/{id} — path: id (UUID)
- GET /api/content-tag-relations/by-content/{contentId} — path: contentId (UUID)
- GET /api/content-tag-relations/by-tag/{tagId} — path: tagId (UUID)

## Comments

- POST /api/comments — body: Comments
- GET /api/comments/{id} — path: id (UUID)
- GET /api/comments — tüm yorumlar
- DELETE /api/comments/{id} — path: id (UUID)
- GET /api/comments/by-content/{contentId} — path: contentId (UUID)
- GET /api/comments/by-user/{userId} — path: userId (UUID)

## Ratings

- POST /api/ratings — body: Rating
- GET /api/ratings/{id} — path: id (UUID)
- GET /api/ratings — tüm puanlar
- DELETE /api/ratings/{id} — path: id (UUID)
- GET /api/ratings/by-content/{contentId} — path: contentId (UUID)
- GET /api/ratings/by-user/{userId} — path: userId (UUID)
- GET /api/ratings/by-user/{userId}/content/{contentId} — path: userId (UUID), contentId (UUID)

## Story Contents

- POST /api/story-contents — body: StoryContents
- GET /api/story-contents/{id} — path: id (UUID)
- GET /api/story-contents — tüm hikaye içerikleri
- DELETE /api/story-contents/{id} — path: id (UUID)
- GET /api/story-contents/by-content/{contentId} — path: contentId (UUID)

## Comic Contents

- POST /api/comic-contents — body: ComicContents
- GET /api/comic-contents/{id} — path: id (UUID)
- GET /api/comic-contents — tüm çizgi içerikleri
- DELETE /api/comic-contents/{id} — path: id (UUID)
- GET /api/comic-contents/by-content/{contentId} — path: contentId (UUID)

## Comic Pages

- POST /api/comic-pages — body: ComicPages
- GET /api/comic-pages/{id} — path: id (UUID)
- GET /api/comic-pages — tüm çizgi sayfaları
- DELETE /api/comic-pages/{id} — path: id (UUID)
- GET /api/comic-pages/by-comic/{comicId} — path: comicId (UUID)

## Painting Contents

- POST /api/painting-contents — body: PaintingContents
- GET /api/painting-contents/{id} — path: id (UUID)
- GET /api/painting-contents — tüm resim içerikleri
- DELETE /api/painting-contents/{id} — path: id (UUID)
- GET /api/painting-contents/by-content/{contentId} — path: contentId (UUID)

## Poetry Contents

- POST /api/poetry-contents — body: PoetryContents
- GET /api/poetry-contents/{id} — path: id (UUID)
- GET /api/poetry-contents — tüm şiir içerikleri
- DELETE /api/poetry-contents/{id} — path: id (UUID)
- GET /api/poetry-contents/by-content/{contentId} — path: contentId (UUID)

## Complaints

- POST /api/complaints — body: Complaints
- GET /api/complaints/{id} — path: id (UUID)
- GET /api/complaints — tüm şikayetler
- DELETE /api/complaints/{id} — path: id (UUID)
- GET /api/complaints/by-target — query: targetId (UUID), targetType (String)
- GET /api/complaints/by-user/{userId} — path: userId (UUID)
