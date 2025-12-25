# MedPadd - Sanat ve Edebiyat Platformu

## 📖 Proje Özeti

MedPadd, yazarlar, şairler, ressamlar ve çizgi roman sanatçılarının eserlerini paylaşabilecekleri ve takipçileriyle iletişim kurabileceği bir sosyal medya ve içerik paylaşım platformudur.

## 🏗️ Sistem Mimarisi

### Backend (Spring Boot 3.5.7)

- **Framework**: Spring Boot 3.5.7 + Spring Data JPA
- **Veritabanı**: PostgreSQL
- **API**: RESTful Web Services
- **Port**: 8080

### Frontend (React + Vite)

- **Framework**: React 19.2.0
- **Build Tool**: Vite 7.2.4
- **Routing**: React Router v6
- **State Management**: Zustand
- **HTTP Client**: Axios
- **Port**: 5173

## 📊 Veritabanı Şeması

### Ana Entityler

#### Users (Kullanıcılar)

- id (UUID)
- firstName, lastName
- username (unique)
- email (unique)
- password
- birthday
- profilePic (Media bağlantısı)
- systemRoles (Role bağlantısı)

#### ContentCreators (İçerik Üreticileri)

- id (UUID)
- user (Users bağlantısı)
- biography

#### Content (İçerik)

- id (UUID)
- contentCreator (ContentCreators bağlantısı)
- title
- price

#### İçerik Tipleri

- **StoryContents**: Hikayeler
- **ComicContents**: Çizgi romanlar
- **PaintingContents**: Resimler
- **PoetryContents**: Şiirler
- **ComicPages**: Çizgi roman sayfaları

#### Etkileşim Entityleri

- **Comments**: Yorumlar
- **Rating**: Puanlar
- **FollowRelations**: Takip ilişkileri

#### Üyelik Sistemi

- **Subscriptions**: Abonelik paketleri
- **UserSubscriptionsRelations**: Kullanıcı abonelik ilişkileri
- **JoinMemberships**: Üyelik katılımları
- **UserJoinMembershipRelations**: Kullanıcı üyelik katılım ilişkileri

#### Badge Sistemi

- **Badges**: Rozetler
- **UserBadgeRelations**: Kullanıcı rozet ilişkileri
- **ContentCreatorVerificationsBadges**: Doğrulama rozetleri

#### Diğer Entityler

- **Media**: Medya dosyaları
- **ContentTags & ContentTagRelations**: İçerik etiketleri
- **ContentCreatorTags & ContentCreatorTagsRelations**: Üretici etiketleri
- **ContentCreatorRoles & ContentCreatorRolesRelations**: Üretici rolleri
- **SystemRoles**: Sistem rolleri
- **Complaints**: Şikayetler

## 🎯 Frontend Sayfaları ve İşlevselliği

### 1. **Authentication** (Giriş & Kayıt)

- ✅ Login Page (`/login`)
  - E-mail/password ile giriş
  - OAuth hazırlığı
  - "Şifremi unuttum" linki
- ✅ Register Page (`/register`)
  - İki aşamalı kayıt süreci
  - Kişisel bilgiler
  - Profil kurulumu
  - İçerik üreticisi seçeneği

### 2. **Home Page** (`/`)

- ✅ Hero section
- ✅ Top creators showcase (top 5)
- ✅ Recent contents grid
- ✅ Content filtering (Story, Comic, Poetry, Painting)
- ✅ Call-to-action section

### 3. **Explore** (`/explore`)

- ✅ Sayfa oluşturulmuş (HomePage kullanılıyor)

### 4. **Content Detail** (`/content/:id`)

- ✅ Content bilgileri
- ✅ Creator info
- ✅ Ratings & Reviews
- ✅ Comment system
- ✅ Rating system
- ✅ Share & Report buttons

### 5. **Creators** (`/creators`)

- ✅ Creator grid
- ✅ Creator search
- ✅ Creator cards with stats
- ✅ Follow button

### 6. **Profile** (`/profile`)

- ✅ User avatar ve banner
- ✅ Profile edit functionality
- ✅ User stats (contents, followers, following)
- ✅ Published contents grid
- ✅ Biography management

### 7. **Coming Soon Pages**

- `/my-contents` - Kişisel içerik yönetimi
- `/subscriptions` - Abonelik yönetimi
- `/following` - Takip edilen listesi
- `/badges` - Rozetler
- `/creator/:id` - Creator detail page

## 🔌 API Bağlantıları

### Yapılandırma

- **Base URL**: `http://localhost:8080/api`
- **Config**: `src/config/api.js`

### Services (`src/services/apiService.js`)

#### Users

```javascript
-create(user) -
  getById(id) -
  getAll() -
  delete id -
  getByUsername(username) -
  getByEmail(email) -
  existsByUsername(username) -
  existsByEmail(email);
```

#### ContentCreators

```javascript
-create(creator) - getById(id) - getAll() - delete id - getByUserId(userId);
```

#### Contents

```javascript
-create(content) -
  getById(id) -
  getAll() -
  delete id -
  getByContentCreator(creatorId);
```

#### Comments

```javascript
-create(comment) -
  getById(id) -
  getAll() -
  delete id -
  getByContent(contentId) -
  getByUser(userId);
```

#### Ratings

```javascript
-create(rating) -
  getById(id) -
  getAll() -
  delete id -
  getByContent(contentId) -
  getByUser(userId) -
  getByUserAndContent(userId, contentId);
```

#### Subscriptions, Badges, Media, Follow Relations

Tüm CRUD operasyonları mevcut

## 📦 State Management (Zustand)

### useAuthStore

```javascript
- user (kullanıcı bilgileri)
- token (auth token)
- setUser(), setToken()
- logout()
- setLoading(), setError()
```

### useContentStore

```javascript
- contents (içerik listesi)
- selectedContent
- addContent(), removeContent(), updateContent()
```

### useCreatorsStore

```javascript
- creators (üreticiler listesi)
- selectedCreator
- addCreator(), removeCreator()
```

### useUIStore

```javascript
-sidebarOpen,
  darkMode - toggleSidebar(),
  toggleDarkMode() - notifications - addNotification(),
  removeNotification();
```

## 🎨 UI Bileşenleri

### Layout

- **Navbar**: Üst navigasyon, arama, tema geçişi
- **Sidebar**: Sol menü, sayfa linkleri (responsive)

### Content

- **ContentCard**: İçerik gösterimi
- **CreatorCard**: Üretici profil kartı
- **CommentForm**: Yorum ekleme
- **RatingForm**: Rating ekleme (5 yıldız)

## 🚀 Kurulum ve Çalıştırma

### Frontend

```bash
# Dependencies kurma
npm install

# Geliştirme sunucusunu başlatma
npm run dev

# Production build
npm build

# Preview
npm preview
```

### Backend

```bash
# Spring Boot uygulamasını başlatma
./mvnw spring-boot:run

# veya
mvn spring-boot:run
```

## 🌐 URL'ler

- **Frontend**: http://localhost:5173
- **Backend API**: http://localhost:8080/api

## 📝 Giriş Akışı

1. Kullanıcı `/login` sayfasında e-mail ve şifre girer
2. API'den kullanıcı doğrulanır
3. Token oluşturulur ve localStorage'a kaydedilir
4. Zustand store güncellenir
5. Kullanıcı ana sayfaya yönlendirilir
6. Sidebar ve navbar otomatik görünür

## 📱 Responsive Design

- Desktop: Full sidebar, normal layout
- Tablet: Collapsible sidebar
- Mobile: Hamburger menu, stacked layout

## 🎯 Sonraki Adımlar

1. **Creator Detail Page** - Üretici profilini görüntüle
2. **My Contents** - İçerik yönetimi
3. **Create Content** - Yeni içerik oluşturma
4. **Search Functionality** - Geliştirilmiş arama
5. **Notifications** - Bildirim sistemi
6. **Payment Integration** - Ödeme sistemi
7. **Real-time Features** - WebSocket entegrasyonu

## 📄 Lisans

MIT

## 👥 Katkıda Bulunma

Katkılar memnuniyetle karşılanır!
