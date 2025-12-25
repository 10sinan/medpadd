import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuthStore } from '../store/store';
import './SubscriptionsPage.css';

function SubscriptionsPage() {
  const navigate = useNavigate();
  const { user } = useAuthStore();
  const [userSubscriptions, setUserSubscriptions] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const [paymentModal, setPaymentModal] = useState(null);
  const [processingPayment, setProcessingPayment] = useState(false);
  const [cardData, setCardData] = useState({
    cardNumber: '',
    cardName: '',
    expiryDate: '',
    cvv: '',
  });

  useEffect(() => {
    if (!user) {
      navigate('/login');
      return;
    }
    fetchSubscriptions();
  }, [user, navigate]);

  const fetchSubscriptions = async () => {
    try {
      setIsLoading(true);
      // TODO: Implement subscription fetching when backend endpoint is ready
      setUserSubscriptions([]);
      setError(null);
    } catch (err) {
      setError('Abonelikler yüklenemedi');
      console.error(err);
    } finally {
      setIsLoading(false);
    }
  };

  const plans = [
    {
      id: 1,
      name: 'Başlangıç',
      price: 9.99,
      currency: 'TRY',
      features: ['Sınırsız içerik okuma', 'Aylık güncelleme'],
      bestFor: 'Başlayanlar için',
    },
    {
      id: 2,
      name: 'Pro',
      price: 19.99,
      currency: 'TRY',
      features: [
        'Sınırsız içerik okuma',
        'Aylık güncelleme',
        'Eksklusif içerik erişimi',
        'Reklam yok',
      ],
      bestFor: 'Düzenli okuyucular',
    },
    {
      id: 3,
      name: 'Premium',
      price: 49.99,
      currency: 'TRY',
      features: [
        'Sınırsız içerik okuma',
        'Aylık güncelleme',
        'Eksklusif içerik erişimi',
        'Reklam yok',
        'Yazarlarla özel chat',
        'Aylık kitaplar',
      ],
      bestFor: 'Tutkulu edebiyat severleri',
    },
  ];

  const isSubscribed = (planId) => {
    return userSubscriptions.some((sub) => sub.planId === planId);
  };

  const handleCardChange = (e) => {
    const { name, value } = e.target;
    setCardData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handlePayment = async (e) => {
    e.preventDefault();

    if (!cardData.cardNumber.trim() || !cardData.cardName.trim() || !cardData.cvv.trim()) {
      alert('Lütfen tüm kart bilgilerini girin');
      return;
    }

    setProcessingPayment(true);

    // Simulate payment processing
    setTimeout(() => {
      try {
        const plan = plans.find((p) => p.id === paymentModal.id);

        // Dummy payment confirmation
        const newSubscription = {
          id: Math.random(),
          planId: paymentModal.id,
          planName: plan.name,
          price: plan.price,
          startDate: new Date().toISOString(),
          endDate: new Date(new Date().setMonth(new Date().getMonth() + 1)).toISOString(),
          status: 'ACTIVE',
        };

        setUserSubscriptions([...userSubscriptions, newSubscription]);

        // Reset form
        setCardData({
          cardNumber: '',
          cardName: '',
          expiryDate: '',
          cvv: '',
        });

        setPaymentModal(null);
        setProcessingPayment(false);

        alert(`✅ Ödeme başarılı! ${plan.name} planına abone oldunuz.`);
      } catch (err) {
        console.error('Ödeme hatası:', err);
        setError('Ödeme işlemi başarısız oldu. Lütfen tekrar deneyiniz.');
        setProcessingPayment(false);
      }
    }, 1500);
  };

  return (
    <div className="subscriptions-page">
      <div className="subscriptions-header">
        <h1>Aboneliklerimiz</h1>
        <p>En uygun planı seçin ve eserlerimizin tamamını keşfedin</p>
      </div>

      {error && <div className="error-message">{error}</div>}

      {isLoading ? (
        <div className="loading">Abonelik planları yükleniyor...</div>
      ) : (
        <>
          <div className="plans-container">
            {plans.map((plan, idx) => (
              <div
                key={plan.id}
                className={`plan-card ${idx === 1 ? 'featured' : ''} ${
                  isSubscribed(plan.id) ? 'subscribed' : ''
                }`}
              >
                {idx === 1 && <div className="featured-badge">En Popüler</div>}
                {isSubscribed(plan.id) && <div className="subscribed-badge">Aktif</div>}

                <h2>{plan.name}</h2>
                <p className="best-for">{plan.bestFor}</p>

                <div className="price">
                  <span className="amount">₺{plan.price}</span>
                  <span className="period">/ay</span>
                </div>

                <ul className="features">
                  {plan.features.map((feature, i) => (
                    <li key={i}>
                      <span className="checkmark">✓</span>
                      {feature}
                    </li>
                  ))}
                </ul>

                <button
                  className={`subscribe-button ${isSubscribed(plan.id) ? 'subscribed' : ''}`}
                  onClick={() => setPaymentModal(plan)}
                  disabled={isSubscribed(plan.id)}
                >
                  {isSubscribed(plan.id) ? '✓ Abone Oldunuz' : 'Şimdi Abone Ol'}
                </button>
              </div>
            ))}
          </div>

          {userSubscriptions.length > 0 && (
            <div className="active-subscriptions">
              <h3>Aktif Abonelikleriniz</h3>
              <div className="subscriptions-list">
                {userSubscriptions.map((sub) => (
                  <div key={sub.id} className="subscription-item">
                    <div className="sub-info">
                      <h4>{sub.planName}</h4>
                      <p>
                        Başlama: {new Date(sub.startDate).toLocaleDateString('tr-TR')}
                      </p>
                      <p className="sub-end">
                        Bitme: {new Date(sub.endDate).toLocaleDateString('tr-TR')}
                      </p>
                    </div>
                    <div className="sub-status">
                      <span className="status-badge active">Aktif</span>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          )}
        </>
      )}

      <div className="subscription-info">
        <h3>Sık Sorulan Sorular</h3>
        <div className="faq-item">
          <h4>Herhangi bir zamanda iptal edebilir miyim?</h4>
          <p>Evet, aboneliğinizi istediğiniz zaman iptal edebilirsiniz. Kalan süre için geri ödeme yapılmaz.</p>
        </div>
        <div className="faq-item">
          <h4>İade politikanız nedir?</h4>
          <p>
            Abone olmanızdan 7 gün içinde para iade politikası geçerlidir. Lütfen destek ekibimizle iletişime geçiniz.
          </p>
        </div>
        <div className="faq-item">
          <h4>Birden fazla abonelik alabilir miyim?</h4>
          <p>Hayır, aynı anda sadece bir plan aktif olabilir. Daha yüksek bir plana yükseltme yapabilirsiniz.</p>
        </div>
      </div>

      {/* Payment Modal */}
      {paymentModal && (
        <div className="modal-overlay" onClick={() => setPaymentModal(null)}>
          <div className="payment-modal" onClick={(e) => e.stopPropagation()}>
            <button className="modal-close" onClick={() => setPaymentModal(null)}>
              ✕
            </button>

            <h2>Ödeme İşlemi</h2>
            <p className="modal-subtitle">
              {paymentModal.name} Planı - ₺{paymentModal.price}/ay
            </p>

            <form onSubmit={handlePayment}>
              <div className="form-group">
                <label htmlFor="cardName">Kart Sahibinin Adı</label>
                <input
                  type="text"
                  id="cardName"
                  name="cardName"
                  placeholder="Adı Soyadı"
                  value={cardData.cardName}
                  onChange={handleCardChange}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="cardNumber">Kart Numarası</label>
                <input
                  type="text"
                  id="cardNumber"
                  name="cardNumber"
                  placeholder="1234 5678 9012 3456"
                  value={cardData.cardNumber}
                  onChange={handleCardChange}
                  maxLength="19"
                  required
                />
              </div>

              <div className="form-row">
                <div className="form-group">
                  <label htmlFor="expiryDate">Son Kullanma Tarihi</label>
                  <input
                    type="text"
                    id="expiryDate"
                    name="expiryDate"
                    placeholder="MM/YY"
                    value={cardData.expiryDate}
                    onChange={handleCardChange}
                    maxLength="5"
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="cvv">CVV</label>
                  <input
                    type="text"
                    id="cvv"
                    name="cvv"
                    placeholder="123"
                    value={cardData.cvv}
                    onChange={handleCardChange}
                    maxLength="3"
                    required
                  />
                </div>
              </div>

              <div className="payment-summary">
                <div className="summary-row">
                  <span>Plan:</span>
                  <strong>{paymentModal.name}</strong>
                </div>
                <div className="summary-row">
                  <span>Tutar:</span>
                  <strong>₺{paymentModal.price}</strong>
                </div>
                <div className="summary-row">
                  <span>Dönem:</span>
                  <strong>1 Ay</strong>
                </div>
              </div>

              <button
                type="submit"
                className="payment-button"
                disabled={processingPayment}
              >
                {processingPayment ? 'İşleniyor...' : '₺' + paymentModal.price + ' Öde'}
              </button>

              <p className="payment-note">
                💳 Test kartı: 4111 1111 1111 1111 (Herhangi bir tarih ve CVV)
              </p>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}

export default SubscriptionsPage;
