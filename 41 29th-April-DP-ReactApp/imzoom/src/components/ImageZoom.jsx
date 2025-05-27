import { useState } from 'react';
import './ImageZoom.css'; // We'll create this next

const ImageZoom = () => {
  const [scale, setScale] = useState(1);
  
  const zoomIn = () => {
    setScale(prevScale => prevScale * 1.2);
  };
  
  const zoomOut = () => {
    setScale(prevScale => Math.max(0.5, prevScale / 1.2));
  };
  
  const resetZoom = () => {
    setScale(1);
  };
  
  return (
    <div className="image-zoom-container">
      <h1>Image Zoom Control</h1>
      <div className="image-box">
        <img 
          src="http://teleuniv.in/sanjaya/student-images/22BD1A051F.jpg" 
          alt="Zoomable" 
          style={{ transform: `scale(${scale})` }}
        />
      </div>
      <div className="controls">
        <button onClick={zoomIn}>Zoom In (+)</button>
        <button onClick={zoomOut}>Zoom Out (-)</button>
        <button onClick={resetZoom}>Reset</button>
      </div>
      <p>Current zoom: {(scale * 100).toFixed(0)}%</p>
    </div>
  );
};

export default ImageZoom;