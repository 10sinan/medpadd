import { useState } from 'react';
import { useAuthStore } from '../store/store';
import { commentsService } from '../services/apiService';
import './CommentForm.css';

function CommentForm({ contentId, onCommented }) {
  const [text, setText] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState('');
  const { user } = useAuthStore();

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!text.trim() || !user) return;

    setIsLoading(true);
    setError('');

    try {
      await commentsService.create({
        content: { id: contentId },
        user: { id: user.id },
        text: text,
      });
      setText('');
      onCommented();
    } catch (err) {
      setError('Yorum gönderilemedi');
      console.error(err);
    } finally {
      setIsLoading(false);
    }
  };

  if (!user) {
    return <p className="auth-required">Yorum yapmak için giriş yapmalısınız.</p>;
  }

  return (
    <form className="comment-form" onSubmit={handleSubmit}>
      {error && <div className="form-error">{error}</div>}
      
      <div className="form-group">
        <textarea
          value={text}
          onChange={(e) => setText(e.target.value)}
          placeholder="Yorumunuzu yazın..."
          rows="3"
          disabled={isLoading}
        />
      </div>

      <button 
        type="submit" 
        className="btn btn-primary"
        disabled={isLoading || !text.trim()}
      >
        {isLoading ? 'Gönderiliyor...' : 'Yorum Gönder'}
      </button>
    </form>
  );
}

export default CommentForm;
