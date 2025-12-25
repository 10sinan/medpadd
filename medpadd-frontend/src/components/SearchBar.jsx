import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { contentsService, contentCreatorsService } from '../services/apiService';
import './SearchBar.css';

function SearchBar() {
  const navigate = useNavigate();
  const [searchTerm, setSearchTerm] = useState('');
  const [results, setResults] = useState(null);
  const [showResults, setShowResults] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  const handleSearch = async (e) => {
    const term = e.target.value;
    setSearchTerm(term);

    if (term.trim().length < 2) {
      setShowResults(false);
      return;
    }

    try {
      setIsLoading(true);
      
      // Search in contents and creators
      const [contentsRes, creatorsRes] = await Promise.all([
        contentsService.getAll(),
        contentCreatorsService.getAll(),
      ]);

      const filteredContents = contentsRes.data.filter(
        (c) =>
          c.title?.toLowerCase().includes(term.toLowerCase()) ||
          c.description?.toLowerCase().includes(term.toLowerCase())
      );

      const filteredCreators = creatorsRes.data.filter(
        (cr) =>
          cr.firstName?.toLowerCase().includes(term.toLowerCase()) ||
          cr.lastName?.toLowerCase().includes(term.toLowerCase()) ||
          cr.username?.toLowerCase().includes(term.toLowerCase())
      );

      setResults({
        contents: filteredContents.slice(0, 5),
        creators: filteredCreators.slice(0, 5),
      });
      setShowResults(true);
    } catch (err) {
      console.error('Arama hatası:', err);
    } finally {
      setIsLoading(false);
    }
  };

  const handleResultClick = (type, id) => {
    setSearchTerm('');
    setShowResults(false);
    if (type === 'content') {
      navigate(`/content/${id}`);
    } else {
      navigate(`/creator/${id}`);
    }
  };

  return (
    <div className="search-bar-container">
      <div className="search-input-wrapper">
        <input
          type="text"
          placeholder="İçerik veya creator ara..."
          value={searchTerm}
          onChange={handleSearch}
          className="search-input"
        />
        <span className="search-icon">🔍</span>
      </div>

      {showResults && results && (
        <div className="search-results">
          {isLoading ? (
            <div className="results-loading">Aranıyor...</div>
          ) : results.contents.length === 0 && results.creators.length === 0 ? (
            <div className="no-results">Sonuç bulunamadı</div>
          ) : (
            <>
              {results.contents.length > 0 && (
                <div className="results-section">
                  <h4>İçerikler</h4>
                  {results.contents.map((content) => (
                    <div
                      key={content.id}
                      className="result-item"
                      onClick={() => handleResultClick('content', content.id)}
                    >
                      <span className="result-icon">📄</span>
                      <span className="result-title">{content.title}</span>
                    </div>
                  ))}
                </div>
              )}

              {results.creators.length > 0 && (
                <div className="results-section">
                  <h4>Creatorlar</h4>
                  {results.creators.map((creator) => (
                    <div
                      key={creator.id}
                      className="result-item"
                      onClick={() => handleResultClick('creator', creator.id)}
                    >
                      <span className="result-icon">👤</span>
                      <span className="result-title">
                        {creator.firstName} {creator.lastName}
                      </span>
                    </div>
                  ))}
                </div>
              )}
            </>
          )}
        </div>
      )}
    </div>
  );
}

export default SearchBar;
