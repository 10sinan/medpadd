import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { usersService, contentCreatorsService } from '../services/apiService';
import './RegisterPage.css';

function RegisterPage() {
  const [step, setStep] = useState(1);
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    confirmPassword: '',
    username: '',
    birthday: '',
    biography: '',
  });
  const [error, setError] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleStep1Submit = async (e) => {
    e.preventDefault();
    setError('');

    if (formData.password !== formData.confirmPassword) {
      setError('Şifreler eşleşmiyor');
      return;
    }

    if (formData.password.length < 6) {
      setError('Şifre en az 6 karakter olmalı');
      return;
    }

    try {
      // Check username
      const usernameExists = await usersService.existsByUsername(formData.username);
      if (usernameExists.data) {
        setError('Bu kullanıcı adı zaten alınmış');
        return;
      }

      // Check email
      const emailExists = await usersService.existsByEmail(formData.email);
      if (emailExists.data) {
        setError('Bu e-mail zaten kayıtlı');
        return;
      }

      setStep(2);
    } catch (err) {
      setError('Doğrulama başarısız oldu');
      console.error(err);
    }
  };

  const handleStep2Submit = async (e) => {
    e.preventDefault();
    setError('');
    setIsLoading(true);

    try {
      // Create user
      const userResponse = await usersService.create({
        firstName: formData.firstName,
        lastName: formData.lastName,
        username: formData.username,
        email: formData.email,
        password: formData.password,
        birthday: formData.birthday,
      });

      const newUser = userResponse.data;

      // Create content creator profile
      if (formData.biography) {
        await contentCreatorsService.create({
          user: newUser,
          biography: formData.biography,
        });
      }

      setStep(3);
    } catch (err) {
      setError('Kayıt başarısız oldu: ' + (err.response?.data?.message || err.message));
      console.error(err);
    } finally {
      setIsLoading(false);
    }
  };

  if (step === 3) {
    return (
      <div className="register-page">
        <div className="register-success">
          <h1>✨ Kayıt Başarılı!</h1>
          <p>Hesabınız oluşturuldu. Şimdi giriş yapabilirsiniz.</p>
          <button 
            className="btn btn-primary"
            onClick={() => navigate('/login')}
          >
            Giriş Yap
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="register-page">
      <div className="register-container">
        <div className="register-header">
          <h1>📚 MedPadd</h1>
          <p>Kayıt Ol</p>
          <div className="step-indicator">
            <div className={`step ${step >= 1 ? 'active' : ''}`}>1</div>
            <div className={`step-line ${step > 1 ? 'active' : ''}`}></div>
            <div className={`step ${step >= 2 ? 'active' : ''}`}>2</div>
          </div>
        </div>

        <form className="register-form" onSubmit={step === 1 ? handleStep1Submit : handleStep2Submit}>
          {error && <div className="register-error">{error}</div>}

          {step === 1 ? (
            <>
              <h2>Kişisel Bilgiler</h2>

              <div className="form-row">
                <div className="form-group">
                  <label htmlFor="firstName">Ad</label>
                  <input
                    type="text"
                    id="firstName"
                    name="firstName"
                    value={formData.firstName}
                    onChange={handleInputChange}
                    placeholder="Ad"
                    required
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="lastName">Soyad</label>
                  <input
                    type="text"
                    id="lastName"
                    name="lastName"
                    value={formData.lastName}
                    onChange={handleInputChange}
                    placeholder="Soyad"
                    required
                  />
                </div>
              </div>

              <div className="form-group">
                <label htmlFor="username">Kullanıcı Adı</label>
                <input
                  type="text"
                  id="username"
                  name="username"
                  value={formData.username}
                  onChange={handleInputChange}
                  placeholder="Kullanıcı adı"
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="email">E-mail Adresi</label>
                <input
                  type="email"
                  id="email"
                  name="email"
                  value={formData.email}
                  onChange={handleInputChange}
                  placeholder="örnek@email.com"
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="password">Şifre</label>
                <input
                  type="password"
                  id="password"
                  name="password"
                  value={formData.password}
                  onChange={handleInputChange}
                  placeholder="Şifreniz"
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="confirmPassword">Şifreyi Onayla</label>
                <input
                  type="password"
                  id="confirmPassword"
                  name="confirmPassword"
                  value={formData.confirmPassword}
                  onChange={handleInputChange}
                  placeholder="Şifrenizi tekrar girin"
                  required
                />
              </div>
            </>
          ) : (
            <>
              <h2>Profil Bilgileri</h2>

              <div className="form-group">
                <label htmlFor="birthday">Doğum Tarihi (İsteğe Bağlı)</label>
                <input
                  type="date"
                  id="birthday"
                  name="birthday"
                  value={formData.birthday}
                  onChange={handleInputChange}
                />
              </div>

              <div className="form-group">
                <label htmlFor="biography">Biyografi (İçerik üreticisi misin?)</label>
                <textarea
                  id="biography"
                  name="biography"
                  value={formData.biography}
                  onChange={handleInputChange}
                  placeholder="Kendini tanıt..."
                  rows="4"
                />
              </div>
            </>
          )}

          <div className="form-actions">
            {step === 2 && (
              <button 
                type="button" 
                className="btn btn-secondary"
                onClick={() => setStep(1)}
              >
                Geri
              </button>
            )}
            <button 
              type="submit" 
              className="btn btn-primary"
              disabled={isLoading}
            >
              {isLoading ? 'İşleniyor...' : step === 1 ? 'Devam Et' : 'Kayıt Ol'}
            </button>
          </div>
        </form>

        <div className="register-footer">
          <p>
            Zaten hesabın var mı? <a href="/login">Giriş Yap</a>
          </p>
        </div>
      </div>
    </div>
  );
}

export default RegisterPage;
