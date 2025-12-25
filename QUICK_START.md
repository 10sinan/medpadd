# 🚀 MedPadd - Hızlı Başlangıç Rehberi

## ⚡ 5 Dakikada Başla

### 1️⃣ **Ön Gereksinimler**

```bash
✓ Node.js v18+ yüklü
✓ npm v9+ yüklü
✓ PostgreSQL running
✓ Spring Boot backend running (port 8080)
```

### 2️⃣ **Frontend Kur**

```bash
cd /home/tiryaki/workspace/medpadd/medpadd-frontend

# Dependencies zaten yüklü (npm install yapılmış)
# Yoksa:
npm install

# Başlat
npm run dev
```

✅ Frontend açılır: **http://localhost:5173**

### 3️⃣ **Backend Kur** (Ayrı terminal)

```bash
cd /home/tiryaki/workspace/medpadd/medpadd

# Başlat
./mvnw spring-boot:run
```

✅ Backend açılır: **http://localhost:8080**

### 4️⃣ **Test Akışı**

```
1. Tarayıcı: http://localhost:5173
2. "Kayıt Ol" tıkla
3. Yeni hesap oluştur
4. Giriş yap
5. Ana sayfada içerik gör
6. İçerik detayına tıkla
7. Yorum & Rating ekle
8. Profil sayfasını ziyaret et
```

---

## 📋 Komut Cheat Sheet

### Frontend Komutları

```bash
# Development
npm run dev          # Başlat (http://localhost:5173)
npm run build        # Production build
npm run preview      # Build preview
npm run lint         # Linting check

# Debugging
npm run dev -- --host    # Network erişim
```

### Backend Komutları

```bash
# Development
./mvnw spring-boot:run   # Başlat (http://localhost:8080)

# Build
./mvnw clean package     # Production build
```

### Database

```bash
# PostgreSQL
psql -U postgres
CREATE DATABASE medpadd;
\c medpadd
# SQL scripts çalıştır
```

---

## 🔗 API Base URL

```javascript
// src/config/api.js
const API_BASE_URL = "http://localhost:8080/api";
```

Backend'de CORS ayarlanması gerekli:

```java
registry.addMapping("/api/**")
    .allowedOrigins("http://localhost:5173")
    .allowedMethods("GET", "POST", "PUT", "DELETE")
```

---

## 🧪 Test User Oluştur

### 1. Manual Test User (Frontend Kaydı)

1. http://localhost:5173/register git
2. Bilgiler gir:
   - Ad: Ahmet
   - Soyad: Yazıcı
   - Username: ahmetyazici
   - Email: ahmet@example.com
   - Password: password123

### 2. Database via SQL

```sql
INSERT INTO users (id, first_name, last_name, username, email, password)
VALUES (
  gen_random_uuid(),
  'Fatma',
  'Ressam',
  'fatmaressam',
  'fatma@example.com',
  'password123'
);
```

---

## 🎯 Sayfa Adresleri

| Sayfa          | URL              | Durum          |
| -------------- | ---------------- | -------------- |
| Home           | `/`              | ✅ Active      |
| Explore        | `/explore`       | ✅ Active      |
| Creators       | `/creators`      | ✅ Active      |
| Content Detail | `/content/:id`   | ✅ Active      |
| Profile        | `/profile`       | ✅ Active      |
| Login          | `/login`         | ✅ Active      |
| Register       | `/register`      | ✅ Active      |
| My Contents    | `/my-contents`   | ⏳ Coming Soon |
| Subscriptions  | `/subscriptions` | ⏳ Coming Soon |
| Following      | `/following`     | ⏳ Coming Soon |
| Badges         | `/badges`        | ⏳ Coming Soon |

---

## 📱 Responsive Testing

### Chrome DevTools

1. F12 açın
2. Ctrl+Shift+M (Responsive mode)
3. Test breakpoints:
   - Mobile: 375px
   - Tablet: 768px
   - Desktop: 1920px

### Mobile Devices

- Android: USB debugging + Chrome
- iOS: Safari Remote Inspector

---

## 🐛 Debugging Tips

### Network Requests

```javascript
// DevTools → Network tab
// Tüm API çağrılarını görebilirsiniz
```

### State Inspection

```javascript
// Browser console
localStorage.getItem("user"); // User info
localStorage.getItem("authToken"); // Auth token
```

### Component Props

```javascript
// React DevTools extension yüklü mi?
// Chrome Web Store → React Developer Tools
```

---

## ⚠️ Sık Sorunlar & Çözümleri

### 1. "Module not found: axios"

```bash
cd medpadd-frontend
npm install axios react-router-dom zustand
```

### 2. "CORS error"

✅ Backend'de CORS enabled mi?

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOrigins("http://localhost:5173")
            .allowedMethods("*");
    }
}
```

### 3. "Cannot GET /api/users"

- Backend çalışıyor mu? (port 8080)
- Doğru endpoint mi?
- PostgreSQL çalışıyor mu?

### 4. "Login başarısız"

- Backend veritabanında user var mı?
- Password doğru mu?
- HTTP status code kontrolü (400/401/500)?

### 5. "Blank page gösteriliyor"

- Browser console'da error var mı? (F12)
- Frontend çalışıyor mu? (http://localhost:5173)
- API URL doğru mu? (src/config/api.js)

---

## 📊 Performance Tips

### Frontend Optimization

```bash
# Build size check
npm run build
# dist/ folder boyutunu kontrol et

# Lighthouse audit
DevTools → Lighthouse
```

### Backend Optimization

```bash
# SQL query performance
EXPLAIN ANALYZE SELECT ...

# Spring Boot metrics
Management endpoints: /actuator
```

---

## 🔐 Security Checklist

### Frontend

- ✅ Token localStorage'de saklanıyor
- ✅ HTTPS ready
- ✅ Password fields
- ✅ XSS prevention (React auto-escapes)

### Backend

- ⏳ JWT token implementation
- ⏳ Password hashing (BCrypt)
- ⏳ Rate limiting
- ⏳ Input validation

---

## 📚 Dosya Referansları

### Konfigurasyonlar

- `src/config/api.js` - API client
- `package.json` - Dependencies
- `vite.config.js` - Build config

### State Management

- `src/store/store.js` - Zustand stores

### Services

- `src/services/apiService.js` - API calls

### Styling

- `src/index.css` - Global styles
- `src/components/*.css` - Component styles
- `src/pages/*.css` - Page styles

---

## 🎓 Dökümentasyon Linkler

1. **COMPLETION_REPORT.md** ← Tamamlanma raporu
2. **PROJECT_STRUCTURE.md** ← Detaylı proje yapısı
3. **INTEGRATION_GUIDE.md** ← Backend entegrasyonu
4. **FRONTEND_README.md** ← Kapsamlı guide
5. **FRONTEND_BUILD_SUMMARY.md** ← İnşaat özeti

---

## 🚀 Production Deployment

### Frontend Build

```bash
npm run build
# dist/ folder'ı web sunucusuna upload et
```

### Nginx Konfigürasyonu

```nginx
server {
    listen 80;
    server_name yourdomain.com;

    root /var/www/medpadd-frontend/dist;
    index index.html;

    location / {
        try_files $uri $uri/ /index.html;
    }

    location /api/ {
        proxy_pass http://backend:8080/api/;
    }
}
```

### Environment Variables

```bash
# .env
VITE_API_URL=https://api.yourdomain.com
```

---

## 💬 Yardım & Destek

### Debug Modu Aç

```bash
# Browser Console
localStorage.setItem('debug', 'true')
```

### Logs Kontrol Et

- Frontend: Browser DevTools → Console
- Backend: Terminal output + `/logs/`

### API Test Et

```bash
# cURL
curl -X GET http://localhost:8080/api/health-check

# Postman
Import endpoints.md
```

---

## ✅ Başarı Göstergesi

Şu şeyleri görebiliyorsanız her şey çalışıyor:

✅ **Frontend**

- [ ] Login sayfası açılıyor
- [ ] Kayıt olabiliyorum
- [ ] Dashboard'a erişebiliyorum
- [ ] İçerikler gösteriliyor

✅ **Backend**

- [ ] Health-check respond ediyor
- [ ] Users endpoint çalışıyor
- [ ] PostgreSQL bağlı
- [ ] Logs gösteriliyor

---

## 🎯 Sonraki Adımlar

1. **Temel Test** - Yukarıdaki tüm kontrol noktalarını test et
2. **DB Seeding** - Test verisi ekle
3. **Feature Testing** - Her sayfayı test et
4. **Performance** - Build size ve load time kontrol et
5. **Security** - HTTPS ve auth test et
6. **Deployment** - Production'a deploy et

---

## 📞 İletişim

Soru veya sorun için:

- Backend issues → Spring Boot docs
- Frontend issues → React docs
- API issues → endpoints.md

---

**Başarılar! Frontend tamamen ready. Enjoy! 🎉**

_Last Updated: 25 Aralık 2025_
