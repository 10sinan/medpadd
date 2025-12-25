import { useState } from 'react';
import { useAuthStore } from '../store/store';
import { ratingsService } from '../services/apiService';
import './RatingForm.css';

function RatingForm({ contentId, onRated }) {
  const [rating, setRating] = useState(0);
  const [hoverRating, setHoverRating] = useState(0);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState('');
  const { user } = useAuthStore();

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!rating || !user) return;

    setIsLoading(true);
    setError('');

    try {
      await ratingsService.create({
        rating: rating,
        user: { id: user.id },
        content: { id: contentId },
      });
      setRating(0);
      onRated();
    } catch (err) {
      setError('Puan verilemedi');
      console.error(err);
    } finally {
      setIsLoading(false);
    }
  };

  if (!user) {
    return <p className="auth-required">Puan vermek için giriş yapmalısınız.</p>;
  }

  return (
    <form className="rating-form" onSubmit={handleSubmit}>
      {error && <div className="form-error">{error}</div>}

      <div className="rating-input">
        {[1, 2, 3, 4, 5].map((value) => (
          <button
            key={value}
            type="button"
            className={`star ${value <= (hoverRating || rating) ? 'active' : ''}`}
            onClick={() => setRating(value)}
            onMouseEnter={() => setHoverRating(value)}
            onMouseLeave={() => setHoverRating(0)}
            disabled={isLoading}
          >
            ★
          </button>
        ))}
      </div>

      <div className="rating-label">
        {rating > 0 && <span>{rating} yıldız</span>}
      </div>

      <button 
        type="submit" 
        className="btn btn-primary"
        disabled={isLoading || !rating}
      >
        {isLoading ? 'Gönderiliyor...' : 'Puan Ver'}
      </button>
    </form>
  );
}

export default RatingForm;
