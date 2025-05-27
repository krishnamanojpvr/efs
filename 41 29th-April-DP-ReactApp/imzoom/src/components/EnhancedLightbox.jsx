import { useState, useEffect } from 'react';
import Lightbox from 'yet-another-react-lightbox';
import Zoom from 'yet-another-react-lightbox/plugins/zoom';
import 'yet-another-react-lightbox/styles.css';
import './EnhancedLightbox.css';

const EnhancedLightbox = () => {
  const images = [
    { src: 'https://images.unsplash.com/photo-1497366216548-37526070297c' },
    { src: 'https://images.unsplash.com/photo-1497366216548-37526070297c' },
    { src: 'https://images.unsplash.com/photo-1497366216548-37526070297c' },
    { src: 'https://images.unsplash.com/photo-1497366216548-37526070297c' },
    { src: 'https://images.unsplash.com/photo-1497366216548-37526070297c' },
    { src: 'https://images.unsplash.com/photo-1497366216548-37526070297c' },
    { src: 'https://images.unsplash.com/photo-1497366216548-37526070297c' },
    { src: 'https://images.unsplash.com/photo-1497366216548-37526070297c' },
    { src: 'https://images.unsplash.com/photo-1497366216548-37526070297c' },
    { src: 'https://images.unsplash.com/photo-1497366216548-37526070297c' },
    { src: 'https://images.unsplash.com/photo-1497366216548-37526070297c' },
  ];

  const [index, setIndex] = useState(-1);
  const [zoomLevel, setZoomLevel] = useState(1);

  // Handle keyboard events
  useEffect(() => {
    const handleKeyDown = (e) => {
      if (index === -1) return; // Only handle keys when lightbox is open
      switch (e.key) {
        case 'ArrowUp':
          e.preventDefault();
          setZoomLevel(prev => Math.min(prev + 0.25, 3));
          break;
        case 'ArrowDown':
          e.preventDefault();
          setZoomLevel(prev => Math.max(prev - 0.25, 1));
          break;
        case 'ArrowLeft':
          e.preventDefault();
          setIndex(prev => (prev - 1 + images.length) % images.length);
          setZoomLevel(1); // Reset zoom on image change
          break;
        // case 'ArrowRight':
        //   e.preventDefault();
        //   setIndex(prev => (prev + 1) % images.length);
        //   setZoomLevel(1); // Reset zoom on image change
        //   break;
        // case 'Escape':
        //   setIndex(-1);
        //   setZoomLevel(1);
        //   break;
        default:
          break;
      }
    };

    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, [index, images.length]);

  return (
    <div className="gallery-container">
      <h1>Enhanced Image Gallery</h1>
      <p>Click on any image to open lightbox. Use arrow keys to navigate and zoom.</p>
      
      <div className="gallery-grid">
        {images.map((image, i) => (
          <div key={i} className="gallery-item" onClick={() => setIndex(i)}>
            <img src={image.src} alt={`Nature ${i + 1}`} />
          </div>
        ))}
      </div>

      <Lightbox
        open={index >= 0}
        index={index}
        close={() => {
          setIndex(-1);
          setZoomLevel(1);
        }}
        slides={images}
        plugins={[Zoom]}
        zoom={{ zoomedIn: zoomLevel > 1 }}
        on={{
          view: ({ index: currentIndex }) => {
            setIndex(currentIndex);
            setZoomLevel(1); // Reset zoom when image changes
          },
        }}
        carousel={{
          finite: false,
        }}
        render={{
          buttonPrev: index <= 0 ? () => null : undefined,
          buttonNext: index >= images.length - 1 ? () => null : undefined,
        }}
        controller={{
          closeOnBackdropClick: true,
          closeOnPullDown: true,
        }}
      />

      {/* Custom zoom controls */}
      {index >= 0 && (
        <div className="zoom-controls">
          <button 
            onClick={(e) => {
              e.stopPropagation();
              setZoomLevel(prev => Math.min(prev + 0.25, 3));
            }}
          >
            Zoom In (+)
          </button>
          <button 
            onClick={(e) => {
              e.stopPropagation();
              setZoomLevel(prev => Math.max(prev - 0.25, 1));
            }}
          >
            Zoom Out (-)
          </button>
          <button 
            onClick={(e) => {
              e.stopPropagation();
              setZoomLevel(1);
            }}
          >
            Reset Zoom
          </button>
          <span>Zoom: {Math.round(zoomLevel * 100)}%</span>
        </div>
      )}
    </div>
  );
};

export default EnhancedLightbox;