import { useState, useEffect } from 'react';
import './DigitalClock.css';

const DigitalClock = () => {
  const [currentTime, setCurrentTime] = useState(new Date());
  const [intervalMinutes, setIntervalMinutes] = useState('');
  const [timeLeft, setTimeLeft] = useState(0);
  const [isRunning, setIsRunning] = useState(false);
  const [startTime, setStartTime] = useState(null);

  // Update current time every second
  useEffect(() => {
    const timer = setInterval(() => {
      setCurrentTime(new Date());
    }, 1000);

    return () => clearInterval(timer);
  }, []);

  // Countdown logic
  useEffect(() => {
    if (!isRunning || timeLeft <= 0) return;

    const countdown = setInterval(() => {
      setTimeLeft(prev => {
        if (prev <= 0) {
          clearInterval(countdown);
          setIsRunning(false);
          return 0;
        }
        return prev - 1;
      });
    }, 1000);

    return () => clearInterval(countdown);
  }, [isRunning, timeLeft]);

  const handleStart = () => {
    if (!intervalMinutes || isNaN(intervalMinutes) || intervalMinutes <= 0) {
      alert('Please enter a valid interval in minutes');
      return;
    }

    const minutes = parseInt(intervalMinutes);
    setTimeLeft(minutes * 60); // Convert to seconds
    setStartTime(new Date());
    setIsRunning(true);
  };

  const handleStop = () => {
    setIsRunning(false);
  };

  const formatTime = (time) => {
    return time.toString().padStart(2, '0');
  };

  const getCurrentTimeString = () => {
    const hours = formatTime(currentTime.getHours());
    const minutes = formatTime(currentTime.getMinutes());
    const seconds = formatTime(currentTime.getSeconds());
    return `${hours}:${minutes}:${seconds}`;
  };

  const getTimeLeftString = () => {
    if (timeLeft <= 0) return '00:00:00';
    
    const hours = Math.floor(timeLeft / 3600);
    const minutes = Math.floor((timeLeft % 3600) / 60);
    const seconds = timeLeft % 60;
    
    return `${formatTime(hours)}:${formatTime(minutes)}:${formatTime(seconds)}`;
  };

  const getRoundedMinutesLeft = () => {
    return Math.ceil(timeLeft / 60);
  };

  return (
    <div className="clock-container">
      <h1>Digital Clock with Interval</h1>
      
      <div className="current-time">
        <h2>Current Time</h2>
        <div className="time-display">{getCurrentTimeString()}</div>
      </div>

      <div className="interval-controls">
        <div className="input-group">
          <label htmlFor="interval">Interval (minutes):</label>
          <input
            type="number"
            id="interval"
            value={intervalMinutes}
            onChange={(e) => setIntervalMinutes(e.target.value)}
            min="1"
            disabled={isRunning}
          />
        </div>

        <div className="button-group">
          <button onClick={handleStart} disabled={isRunning}>
            Start
          </button>
          <button onClick={handleStop} disabled={!isRunning}>
            Stop
          </button>
        </div>
      </div>

      <div className="interval-display">
        <h2>{isRunning ? 'Time Remaining' : 'Time Left When Stopped'}</h2>
        {isRunning ? (
          <div className="time-display">{getTimeLeftString()}</div>
        ) : (
          <div className="time-display">
            {timeLeft > 0 ? `${getRoundedMinutesLeft()} minute(s)` : '--:--:--'}
          </div>
        )}
      </div>

      {timeLeft <= 0 && isRunning && (
        <div className="alert">Interval completed!</div>
      )}
    </div>
  );
};

export default DigitalClock;