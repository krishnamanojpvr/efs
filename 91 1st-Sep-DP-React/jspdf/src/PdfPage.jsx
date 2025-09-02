import React, { useState } from "react";
import { generateResumePdf } from "./pdfUtil";

export default function PdfPage() {
  const [phone, setPhone] = useState("");
  const [resume, setResume] = useState(null);
  const [error, setError] = useState("");
  const [pdfUrl, setPdfUrl] = useState("");

  const fetchResume = async () => {
    setError("");
    setResume(null);
    setPdfUrl("");
    if (!phone) {
      setError("Enter phone number");
      return;
    }
    try {
      const res = await fetch(`http://localhost:5000/api/resume/${phone}`);
      if (!res.ok) throw new Error("Resume not found");
      const data = await res.json();
      setResume(data);
    } catch (err) {
      setError(err.message);
    }
  };

  const handleGeneratePdf = () => {
    if (!resume) return;
    const doc = generateResumePdf(resume);
    const pdfBlob = doc.output("blob");
    const url = URL.createObjectURL(pdfBlob);
    setPdfUrl(url);
  };

  const handleOpenPdf = () => {
    if (pdfUrl) {
      window.open(pdfUrl, "_blank");
    }
  };

  return (
    <div className="max-w-xl mx-auto p-6 bg-white rounded shadow mt-8">
      <h2 className="text-2xl font-bold mb-4">Get Resume PDF</h2>
      <div className="flex gap-2 mb-4">
        <input
          type="text"
          value={phone}
          onChange={(e) => setPhone(e.target.value)}
          placeholder="Enter phone number"
          className="input"
        />
        <button className="btn" onClick={fetchResume}>Get Resume</button>
      </div>
      {error && <div className="text-red-500">{error}</div>}
      {resume && (
        <div className="mt-4 space-y-4">
          <button className="btn" onClick={handleGeneratePdf}>Generate PDF</button>
          {pdfUrl && (
            <div className="flex gap-2 items-center">
              <iframe
                src={pdfUrl}
                title="Resume PDF"
                width="100%"
                height="500px"
                className="border rounded"
              />
              <button className="btn bg-green-600 hover:bg-green-700" onClick={handleOpenPdf} type="button">
                Open PDF in New Tab
              </button>
            </div>
          )}
        </div>
      )}
    </div>
  );
}
