import { useEffect, useState } from 'react';
import { contentCreatorsService } from '../services/apiService';
import CreatorCard from '../components/CreatorCard';
import './CreatorsPage.css';

function CreatorsPage() {
  const [creators, setCreators] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    fetchCreators();
  }, []);

  const fetchCreators = async () => {
    setIsLoading(true);
    try {
      const response = await contentCreatorsService.getAll();
      setCreators(response.data);
    } catch (err) {
      console.error(err);
    } finally {
      setIsLoading(false);
    }
  };

  const filteredCreators = creators.filter((creator) =>
    creator.user?.firstName?.toLowerCase().includes(searchTerm.toLowerCase()) ||
    creator.user?.lastName?.toLowerCase().includes(searchTerm.toLowerCase()) ||
    creator.user?.username?.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="creators-page">
      <div className="creators-header">
        <h1>👨‍🎨 İçerik Üreticileri</h1>
        <p>MedPadd'deki en yetenekli sanatçı ve yazarları keşfet</p>
      </div>

      <div className="creators-container">
        <div className="creators-search">
          <input
            type="text"
            placeholder="İçerik üreticisi ara..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="search-input"
          />
        </div>

        {isLoading ? (
          <div className="loading">Yükleniyor...</div>
        ) : (
          <div className="creators-grid">
            {filteredCreators.length > 0 ? (
              filteredCreators.map((creator) => (
                <CreatorCard key={creator.id} creator={creator} />
              ))
            ) : (
              <div className="no-results">
                Sonuç bulunamadı
              </div>
            )}
          </div>
        )}
      </div>
    </div>
  );
}

export default CreatorsPage;
