import { useState } from 'react';
import Lightbox from 'yet-another-react-lightbox';
import 'yet-another-react-lightbox/styles.css';
import './ImageGallery.css';

const ImageGallery = () => {
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

  const [isOpen, setIsOpen] = useState(false);
  const [currentIndex, setCurrentIndex] = useState(0);

  return (
    <div className="gallery-container">
      <h1>Image Gallery</h1>
      <p>Click on any image to open lightbox</p>
      
      <div className="gallery-grid">
        {images.map((img, index) => (
          <div 
            key={index} 
            className="gallery-item"
            onClick={() => {
              setCurrentIndex(index);
              setIsOpen(true);
            }}
          >
            <img src={img.src} alt={`Gallery item ${index + 1}`} />
          </div>
        ))}
      </div>

      <Lightbox
        open={isOpen}
        close={() => setIsOpen(false)}
        slides={images}
        index={currentIndex}
        controller={{ closeOnBackdropClick: true }}
      />
    </div>
  );
};

export default ImageGallery;