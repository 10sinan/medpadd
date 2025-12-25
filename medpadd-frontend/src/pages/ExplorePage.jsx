import { useEffect, useState } from 'react';
import { useContentStore } from '../store/store';
import { contentsService } from '../services/apiService';
import ContentCard from '../components/ContentCard';
import './ExplorePage.css';

function ExplorePage() {
  const { contents, setContents, isLoading, setLoading, error, setError } = useContentStore();
  const [filter, setFilter] = useState('all');
  const [sortBy, setSortBy] = useState('newest');
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    fetchContents();
  }, [filter, sortBy]);

  const fetchContents = async () => {
    setLoading(true);
    try {
      const response = await contentsService.getAll();
      let filtered = response.data;

      // Filter by type
      if (filter !== 'all') {
        filtered = filtered.filter(
          (c) => c.contentType?.toLowerCase() === filter.toLowerCase()
        );
      }

      // Filter by search term
      if (searchTerm.trim()) {
        filtered = filtered.filter(
          (c) =>
            c.title?.toLowerCase().includes(searchTerm.toLowerCase()) ||
            c.description?.toLowerCase().includes(searchTerm.toLowerCase())
        );
      }

      // Sort
      if (sortBy === 'newest') {
        filtered.sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
      } else if (sortBy === 'popular') {
        filtered.sort((a, b) => (b.averageRating || 0) - (a.averageRating || 0));
      } else if (sortBy === 'price-low') {
        filtered.sort((a, b) => (a.price || 0) - (b.price || 0));
      } else if (sortBy === 'price-high') {
        filtered.sort((a, b) => (b.price || 0) - (a.price || 0));
      }

      setContents(filtered);
      setError(null);
    } catch (err) {
      setError('İçerikler yüklenemedi');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const contentTypes = ['all', 'story', 'comic', 'painting', 'poetry'];
  const typeLabels = {
    all: 'Tümü',
    story: 'Hikaye',
    comic: 'Çizgi Roman',
    painting: 'Resim',
    poetry: 'Şiir',
  };

  const filteredContents = contents.filter(
    (c) =>
      !searchTerm.trim() ||
      c.title?.toLowerCase().includes(searchTerm.toLowerCase()) ||
      c.description?.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="explore-page">
      <div className="explore-header">
        <h1>İçerikleri Keşfet</h1>
        <p>Binlerce eser arasından aradığınızı bulun</p>
      </div>

      {error && <div className="error-message">{error}</div>}

      <div className="explore-controls">
        <div className="search-section">
          <input
            type="text"
            placeholder="Başlık veya açıklama ara..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="search-input"
          />
        </div>

        <div className="filters-section">
          <div className="filter-group">
            <label>Tür:</label>
            <div className="filter-buttons">
              {contentTypes.map((type) => (
                <button
                  key={type}
                  className={`filter-btn ${filter === type ? 'active' : ''}`}
                  onClick={() => setFilter(type)}
                >
                  {typeLabels[type]}
                </button>
              ))}
            </div>
          </div>

          <div className="filter-group">
            <label htmlFor="sort">Sırala:</label>
            <select
              id="sort"
              value={sortBy}
              onChange={(e) => setSortBy(e.target.value)}
              className="sort-select"
            >
              <option value="newest">En Yeni</option>
              <option value="popular">En Popüler</option>
              <option value="price-low">Fiyat (Düşükten Yükseke)</option>
              <option value="price-high">Fiyat (Yüksekten Düşüke)</option>
            </select>
          </div>
        </div>
      </div>

      {isLoading ? (
        <div className="loading">İçerikler yükleniyor...</div>
      ) : filteredContents.length === 0 ? (
        <div className="no-results">
          <h2>Sonuç bulunamadı</h2>
          <p>Arama kriterlerinize uygun içerik bulunamadı. Filtreleri değiştirerek denemeyi biraz.</p>
        </div>
      ) : (
        <>
          <div className="results-info">
            <p>{filteredContents.length} sonuç bulundu</p>
          </div>

          <div className="content-grid">
            {filteredContents.map((content) => (
              <ContentCard key={content.id} content={content} />
            ))}
          </div>
        </>
      )}
    </div>
  );
}

export default ExplorePage;
