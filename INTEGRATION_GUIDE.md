# MedPadd Frontend-Backend Integration Guide

## 🔗 Bağlantı Konfigürasyonu

### API Base URL Ayarı

`src/config/api.js` dosyasında backend adresini ayarlayın:

```javascript
const API_BASE_URL = "http://localhost:8080/api";
```

## 🛠️ Backend Hazırlık

### 1. CORS Konfigürasyonu

Backend'de CORS izni ekleyin (WebConfig.java):

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOrigins("http://localhost:5173", "http://localhost:3000")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true)
            .maxAge(3600);
    }
}
```

### 2. Authentication Implementation

Gerçek JWT token uygulaması için:

```java
// AuthController ekleyin
@PostMapping("/auth/login")
public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
    // Doğrulama
    // JWT token oluştur
    // Token döndür
}
```

Frontend'de token kullanımı otomatik olarak `src/config/api.js`'de yapılıyor.

## 🔑 Login Flow

### Backend

1. E-mail ve password al
2. Kullanıcıyı veritabanında bul
3. Password hash'ini kontrol et (BCrypt)
4. JWT token oluştur
5. Token ve user bilgilerini döndür

### Frontend (Hazır)

```javascript
// LoginPage.jsx'de yapılmış
const userResponse = await usersService.getByEmail(email);
const user = userResponse.data;
setUser(user);
setToken("jwt-token");
```

## 📝 Veritabanı Seeding

Test etmek için örnek veri ekleyin:

```sql
INSERT INTO users (id, first_name, last_name, username, email, password, role_id)
VALUES
  ('uuid1', 'Ahmet', 'Yazıcı', 'ahmetyazici', 'ahmet@example.com', 'hashed_password', 'role_id'),
  ('uuid2', 'Fatma', 'Ressam', 'fatmaressam', 'fatma@example.com', 'hashed_password', 'role_id');

INSERT INTO content_creators (id, user_id, biography)
VALUES
  ('uuid3', 'uuid1', 'Sayfa yazarı'),
  ('uuid4', 'uuid2', 'Ressam');

INSERT INTO contents (id, content_creator_id, title, price)
VALUES
  ('uuid5', 'uuid3', 'Örnek Hikaye', 9.99),
  ('uuid6', 'uuid4', 'Örnek Resim', 4.99);
```

## 🚀 Başlatma Sırası

1. **PostgreSQL'i başlatın**

```bash
# Docker kullanıyorsanız
docker run --name medpadd-db -e POSTGRES_PASSWORD=password -p 5432:5432 postgres
```

2. **Backend'i başlatın**

```bash
cd medpadd
./mvnw spring-boot:run
```

3. **Frontend'i başlatın**

```bash
cd medpadd-frontend
npm run dev
```

4. **Browser'ı açın**

```
http://localhost:5173
```

## ✅ Test Çeklist

### Authentication

- [ ] Kayıt olabiliyorum
- [ ] Giriş yapabiliyorum
- [ ] Token localStorage'da kaydediliyor
- [ ] Logout çalışıyor
- [ ] 401 error'da login sayfasına yönlendiriliyor

### Home Page

- [ ] İçerikler yükleniyor
- [ ] Üreticiler gösteriliyor
- [ ] Filter butonları çalışıyor
- [ ] Content card'a tıklayınca detail sayfasına gidiyor

### Content Detail

- [ ] İçerik bilgileri doğru
- [ ] Yorum ekleyebiliyorum
- [ ] Rating verebiliyorum
- [ ] Yorumlar listeleniyior
- [ ] Puanlar gösteriliyor

### Creators Page

- [ ] Üreticiler listeleniyor
- [ ] Arama çalışıyor
- [ ] Üreticiye tıklayınca detail sayfasına gidiyor

### Profile Page

- [ ] Profil bilgileri doğru
- [ ] Profili düzenleyebiliyorum
- [ ] İçeriklerim listeleniyor
- [ ] Istatistikler gösteriliyor

## 🔄 API Request/Response Examples

### Login

```javascript
// Request
POST /api/users/by-email/test@example.com

// Response
{
  "id": "uuid",
  "firstName": "John",
  "lastName": "Doe",
  "username": "johndoe",
  "email": "test@example.com",
  "password": "hashed",
  "birthday": "2000-01-01"
}
```

### Create Comment

```javascript
// Request
POST /api/comments
{
  "content": { "id": "content-uuid" },
  "user": { "id": "user-uuid" },
  "text": "Harika bir eser!"
}

// Response
{
  "id": "comment-uuid",
  "content": { "id": "content-uuid" },
  "user": { "id": "user-uuid" },
  "text": "Harika bir eser!",
  "createdAt": "2025-12-25T19:30:00"
}
```

### Create Rating

```javascript
// Request
POST /api/ratings
{
  "rating": 5,
  "user": { "id": "user-uuid" },
  "content": { "id": "content-uuid" }
}

// Response
{
  "id": "rating-uuid",
  "rating": 5,
  "user": { "id": "user-uuid" },
  "content": { "id": "content-uuid" },
  "createdAt": "2025-12-25T19:30:00"
}
```

## 🐛 Debugging

### Frontend Debug

```javascript
// Browser console'da
localStorage.getItem("user");
localStorage.getItem("authToken");
```

### Network Requests

Chrome DevTools > Network tab'da API isteklerini kontrol edin.

### Backend Logs

```bash
tail -f target/logs/medpadd.log
```

## 📞 API Endpoints Hızlı Referans

Tüm endpoints için bkz: `medpadd/endpoints.md`

### Most Used Endpoints

| Method | Endpoint                             | Kullanım              |
| ------ | ------------------------------------ | --------------------- |
| GET    | /api/users                           | Tüm kullanıcılar      |
| GET    | /api/contents                        | Tüm içerikler         |
| POST   | /api/comments                        | Yorum ekle            |
| POST   | /api/ratings                         | Rating ver            |
| GET    | /api/content-creators                | Tüm üreticiler        |
| GET    | /api/contents/by-content-creator/:id | Üreticinin içerikleri |

## 🎯 Production Deployment

### Frontend Build

```bash
npm run build
# dist/ folder'ı web sunucusuna (nginx, apache) deploy et
```

### Backend Build

```bash
./mvnw clean package
# target/medpadd-0.0.1-SNAPSHOT.jar dosyasını deploy et
java -jar medpadd-0.0.1-SNAPSHOT.jar
```

### Environment Variables

`.env` dosyası oluştur (frontend):

```
VITE_API_URL=https://api.example.com/api
```

Backend `application-prod.properties`:

```properties
spring.datasource.url=jdbc:postgresql://db-host:5432/medpadd
spring.datasource.username=prod-user
spring.datasource.password=secure-password
```

## 📚 Kaynaklar

- [React Documentation](https://react.dev)
- [React Router](https://reactrouter.com)
- [Zustand](https://github.com/pmndrs/zustand)
- [Axios](https://axios-http.com)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [PostgreSQL](https://www.postgresql.org/)
