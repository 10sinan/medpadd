import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuthStore } from '../store/store';
import { contentsService, contentCreatorsService } from '../services/apiService';
import './CreateContentPage.css';

function CreateContentPage() {
  const navigate = useNavigate();
  const { user } = useAuthStore();
  const [step, setStep] = useState(1);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState(false);
  const [successMessage, setSuccessMessage] = useState('');
  const [fieldErrors, setFieldErrors] = useState({});

  const [formData, setFormData] = useState({
    title: '',
    description: '',
    contentType: 'STORY',
    price: 0,
    coverImageUrl: '',
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
    setError('');
    // Clear field-specific error
    if (fieldErrors[name]) {
      setFieldErrors((prev) => ({
        ...prev,
        [name]: '',
      }));
    }
  };

  const validateStep1 = () => {
    const errors = {};

    if (!formData.title.trim()) {
      errors.title = 'Başlık zorunludur';
    } else if (formData.title.length < 3) {
      errors.title = 'Başlık en az 3 karakter olmalıdır';
    } else if (formData.title.length > 100) {
      errors.title = 'Başlık en fazla 100 karakter olmalıdır';
    }

    if (!formData.description.trim()) {
      errors.description = 'Açıklama zorunludur';
    } else if (formData.description.length < 10) {
      errors.description = 'Açıklama en az 10 karakter olmalıdır';
    } else if (formData.description.length > 2000) {
      errors.description = 'Açıklama en fazla 2000 karakter olmalıdır';
    }

    if (!formData.contentType) {
      errors.contentType = 'İçerik türü seçiniz';
    }

    setFieldErrors(errors);
    return Object.keys(errors).length === 0;
  };

  const validateStep2 = () => {
    const errors = {};

    if (formData.price < 0) {
      errors.price = 'Fiyat negatif olamaz';
    } else if (formData.price > 10000) {
      errors.price = 'Fiyat en fazla 10000 TRY olabilir';
    }

    if (formData.coverImageUrl && !isValidUrl(formData.coverImageUrl)) {
      errors.coverImageUrl = 'Geçerli bir URL girin';
    }

    setFieldErrors(errors);
    return Object.keys(errors).length === 0;
  };

  const isValidUrl = (string) => {
    try {
      new URL(string);
      return true;
    } catch (_) {
      return false;
    }
  };

  const handleCreateContent = async (e) => {
    e.preventDefault();

    if (step === 1) {
      if (validateStep1()) {
        setStep(2);
      }
      return;
    }

    if (!validateStep2()) {
      return;
    }

    try {
      setIsLoading(true);
      setError('');
      setSuccess(false);

      // Get or create creator profile
      let creatorId;
      try {
        const creatorRes = await contentCreatorsService.getByUserId(user.id);
        creatorId = creatorRes.data.id;
      } catch {
        // Create new creator profile
        const newCreatorRes = await contentCreatorsService.create({
          userId: user.id,
          firstName: user.firstName || 'Creator',
          lastName: user.lastName || '',
          biography: '',
        });
        creatorId = newCreatorRes.data.id;
      }

      // Create content
      const contentPayload = {
        title: formData.title,
        description: formData.description,
        contentType: formData.contentType,
        price: parseFloat(formData.price) || 0,
        coverImageUrl:
          formData.coverImageUrl ||
          `https://via.placeholder.com/400?text=${encodeURIComponent(formData.title)}`,
        contentCreatorId: creatorId,
      };

      const response = await contentsService.create(contentPayload);

      // Success - Show success message then redirect
      setSuccess(true);
      setSuccessMessage('✅ İçerik başarıyla oluşturuldu!');
      
      // Reset form
      setFormData({
        title: '',
        description: '',
        contentType: 'STORY',
        price: 0,
        coverImageUrl: '',
      });
      setStep(1);
      
      // Redirect after 2 seconds
      setTimeout(() => {
        navigate(`/content/${response.data.id}`, {
          state: { message: 'İçerik başarıyla oluşturuldu!' },
        });
      }, 2000);
    } catch (err) {
      const errorMsg = err.response?.data?.message || 'İçerik oluşturulurken hata oluştu';
      setError('❌ ' + errorMsg);
      console.error(err);
      window.scrollTo(0, 0);
    } finally {
      setIsLoading(false);
    }
  };

  const contentTypes = ['STORY', 'COMIC', 'PAINTING', 'POETRY'];

  return (
    <div className="create-content-page">
      <div className="create-container">
        <div className="create-header">
          <h1>Yeni İçerik Oluştur</h1>
          <p>Sanat eserinizi paylaşın ve takipçilerinizle bağlantı kurun</p>
        </div>

        <div className="step-indicator">
          <div className={`step ${step === 1 ? 'active' : 'completed'}`}>1</div>
          <div className="step-line"></div>
          <div className={`step ${step === 2 ? 'active' : ''}`}>2</div>
        </div>

        <form onSubmit={handleCreateContent} className="create-form">
          {success && (
            <div className="success-message">
              <span className="success-icon">✅</span>
              <strong>{successMessage}</strong>
              <p>Yönlendiriliyorsunuz...</p>
            </div>
          )}

          {error && <div className="error-message">{error}</div>}

          {step === 1 && (
            <div className="form-step">
              <h2>Temel Bilgiler</h2>

              <div className="form-group">
                <label htmlFor="title">Başlık * {fieldErrors.title && <span className="error-text">({fieldErrors.title})</span>}</label>
                <input
                  type="text"
                  id="title"
                  name="title"
                  placeholder="İçeriğinizin başlığını girin"
                  value={formData.title}
                  onChange={handleInputChange}
                  className={fieldErrors.title ? 'error' : ''}
                  required
                />
                <small>{formData.title.length}/100</small>
              </div>

              <div className="form-group">
                <label htmlFor="contentType">İçerik Türü * {fieldErrors.contentType && <span className="error-text">({fieldErrors.contentType})</span>}</label>
                <select
                  id="contentType"
                  name="contentType"
                  value={formData.contentType}
                  onChange={handleInputChange}
                  className={fieldErrors.contentType ? 'error' : ''}
                >
                  {contentTypes.map((type) => (
                    <option key={type} value={type}>
                      {type === 'STORY'
                        ? 'Hikaye'
                        : type === 'COMIC'
                          ? 'Çizgi Roman'
                          : type === 'PAINTING'
                            ? 'Resim'
                            : 'Şiir'}
                    </option>
                  ))}
                </select>
              </div>

              <div className="form-group">
                <label htmlFor="description">Açıklama * {fieldErrors.description && <span className="error-text">({fieldErrors.description})</span>}</label>
                <textarea
                  id="description"
                  name="description"
                  placeholder="İçeriğinizin detaylı açıklamasını girin"
                  rows="5"
                  value={formData.description}
                  onChange={handleInputChange}
                  className={fieldErrors.description ? 'error' : ''}
                  required
                ></textarea>
                <small>{formData.description.length}/2000</small>
              </div>

              <button
                type="button"
                className="next-button"
                onClick={() => {
                  if (validateStep1()) {
                    setStep(2);
                  }
                }}
              >
                Devam Et →
              </button>
            </div>
          )}

          {step === 2 && (
            <div className="form-step">
              <h2>Fiyatlandırma & Görünüm</h2>

              <div className="form-group">
                <label htmlFor="price">Fiyat (₺) {fieldErrors.price && <span className="error-text">({fieldErrors.price})</span>}</label>
                <input
                  type="number"
                  id="price"
                  name="price"
                  placeholder="0 = Ücretsiz"
                  value={formData.price}
                  onChange={handleInputChange}
                  min="0"
                  step="0.01"
                  className={fieldErrors.price ? 'error' : ''}
                />
                <small>0 giriş yaparsanız içerik ücretsiz olacaktır</small>
              </div>

              <div className="form-group">
                <label htmlFor="coverImageUrl">Kapak Resmi URL {fieldErrors.coverImageUrl && <span className="error-text">({fieldErrors.coverImageUrl})</span>}</label>
                <input
                  type="url"
                  id="coverImageUrl"
                  name="coverImageUrl"
                  placeholder="https://example.com/image.jpg"
                  value={formData.coverImageUrl}
                  onChange={handleInputChange}
                  className={fieldErrors.coverImageUrl ? 'error' : ''}
                />
                <small>Boş bırakırsanız varsayılan görüntü kullanılacaktır</small>
              </div>

              {formData.coverImageUrl && (
                <div className="image-preview">
                  <p>Önizleme:</p>
                  <img src={formData.coverImageUrl} alt="Preview" />
                </div>
              )}

              <div className="button-group">
                <button
                  type="button"
                  className="back-button"
                  onClick={() => setStep(1)}
                >
                  ← Geri
                </button>
                <button
                  type="submit"
                  className="submit-button"
                  disabled={isLoading || success}
                >
                  {isLoading ? '⏳ Oluşturuluyor...' : success ? '✅ Başarılı!' : '📤 İçeriği Oluştur'}
                </button>
              </div>
            </div>
          )}
        </form>
      </div>
    </div>
  );
}

export default CreateContentPage;
