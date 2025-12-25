import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuthStore } from '../store/store';
import { usersService } from '../services/apiService';
import './LoginPage.css';

function LoginPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const { setUser, setToken } = useAuthStore();
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setIsLoading(true);

    try {
      // Email ile kullanıcıyı bul
      const userResponse = await usersService.getByEmail(email);
      const user = userResponse.data;

      // Password kontrolü (gerçek projede JWT token kullanılmalı)
      if (user && user.password === password) {
        setUser(user);
        setToken('mock-token-' + user.id);
        navigate('/');
      } else {
        setError('E-mail veya şifre hatalı');
      }
    } catch (err) {
      setError('Giriş başarısız oldu. Lütfen bilgilerinizi kontrol edin.');
      console.error(err);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="auth-page">
      <div className="auth-container">
        <div className="auth-header">
          <h1>📚 MedPadd</h1>
          <p>Giriş Yap</p>
        </div>

        <form className="auth-form" onSubmit={handleSubmit}>
          {error && <div className="auth-error">{error}</div>}

          <div className="form-group">
            <label htmlFor="email">E-mail Adresi</label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="örnek@email.com"
              required
              disabled={isLoading}
            />
          </div>

          <div className="form-group">
            <label htmlFor="password">Şifre</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="Şifreniz"
              required
              disabled={isLoading}
            />
          </div>

          <button 
            type="submit" 
            className="auth-btn"
            disabled={isLoading}
          >
            {isLoading ? 'Giriş yapılıyor...' : 'Giriş Yap'}
          </button>

          <div className="auth-divider">veya</div>

          <button type="button" className="social-btn google-btn" disabled={isLoading}>
            Google ile Giriş Yap
          </button>
        </form>

        <div className="auth-footer">
          <p>
            Hesabın yok mu? <a href="/register">Kayıt Ol</a>
          </p>
          <p>
            <a href="/forgot-password">Şifremi Unuttum</a>
          </p>
        </div>
      </div>

      <div className="auth-hero">
        <div className="auth-hero-content">
          <h2>Sanat ve Edebiyatın Merkezi</h2>
          <p>Binlerce yazı, resim, şiir ve çizgi romanı keşfedin ve kendi eserlerinizi paylaşın.</p>
          <ul className="auth-features">
            <li>✨ Sınırsız İçerik Keşfet</li>
            <li>🎨 Kendi İçeriğini Oluştur</li>
            <li>💰 Para Kazan</li>
            <li>🌟 Topluluk Oluştur</li>
          </ul>
        </div>
      </div>
    </div>
  );
}

export default LoginPage;
