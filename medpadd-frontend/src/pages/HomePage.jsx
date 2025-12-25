import { useEffect, useState } from 'react';
import { useContentStore, useCreatorsStore } from '../store/store';
import { contentsService, contentCreatorsService } from '../services/apiService';
import ContentCard from '../components/ContentCard';
import CreatorCard from '../components/CreatorCard';
import './HomePage.css';

function HomePage() {
  const { contents, setContents, isLoading, setLoading, error, setError } = useContentStore();
  const { creators, setCreators } = useCreatorsStore();
  const [filter, setFilter] = useState('all');

  useEffect(() => {
    fetchContents();
    fetchCreators();
  }, []);

  const fetchContents = async () => {
    setLoading(true);
    try {
      const response = await contentsService.getAll();
      setContents(response.data);
      setError(null);
    } catch (err) {
      setError('İçerikler yüklenemedi');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const fetchCreators = async () => {
    try {
      const response = await contentCreatorsService.getAll();
      setCreators(response.data);
    } catch (err) {
      console.error(err);
    }
  };

  const topCreators = creators.slice(0, 5);
  const recentContents = contents.slice(0, 12);

  return (
    <div className="home-page">
      <section className="hero">
        <div className="hero-content">
          <h1>Sanat ve Edebiyat Dünyasına Hoş Geldiniz</h1>
          <p>Binlerce yazı, resim, şiir ve çizgi roman keşfedin</p>
          <div className="hero-buttons">
            <button className="btn btn-primary">Keşfetmeye Başla</button>
            <button className="btn btn-secondary">İçerik Oluştur</button>
          </div>
        </div>
      </section>

      <section className="section">
        <div className="section-header">
          <h2>⭐ En İyi İçerik Üreticileri</h2>
        </div>
        <div className="creators-grid">
          {topCreators.map((creator) => (
            <CreatorCard key={creator.id} creator={creator} />
          ))}
        </div>
      </section>

      <section className="section">
        <div className="section-header">
          <h2>🎨 Son Eklenen İçerikler</h2>
          <div className="filter-buttons">
            <button 
              className={`filter-btn ${filter === 'all' ? 'active' : ''}`}
              onClick={() => setFilter('all')}
            >
              Tümü
            </button>
            <button 
              className={`filter-btn ${filter === 'story' ? 'active' : ''}`}
              onClick={() => setFilter('story')}
            >
              Hikaye
            </button>
            <button 
              className={`filter-btn ${filter === 'comic' ? 'active' : ''}`}
              onClick={() => setFilter('comic')}
            >
              Çizgi Roman
            </button>
            <button 
              className={`filter-btn ${filter === 'poetry' ? 'active' : ''}`}
              onClick={() => setFilter('poetry')}
            >
              Şiir
            </button>
            <button 
              className={`filter-btn ${filter === 'painting' ? 'active' : ''}`}
              onClick={() => setFilter('painting')}
            >
              Resim
            </button>
          </div>
        </div>

        {isLoading ? (
          <div className="loading">Yükleniyor...</div>
        ) : error ? (
          <div className="error">{error}</div>
        ) : (
          <div className="contents-grid">
            {recentContents.map((content) => (
              <ContentCard key={content.id} content={content} />
            ))}
          </div>
        )}
      </section>

      <section className="section cta">
        <h2>Kendi İçeriklerini Paylaşmaya Hazır Mısın?</h2>
        <p>MedPadd'de içerik üreticisi olarak katılın ve geniş bir kitleye ulaşın</p>
        <button className="btn btn-primary">İçerik Üreticisi Ol</button>
      </section>
    </div>
  );
}

export default HomePage;
