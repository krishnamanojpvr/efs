import React, { useState } from "react";
import ResumeForm from "./ResumeForm";
import PdfPage from "./PdfPage";

function App() {
  const [page, setPage] = useState("form");
  const [success, setSuccess] = useState("");

  const handleSubmit = async (form) => {
    setSuccess("");
    try {
      const res = await fetch("http://localhost:5000/api/resume", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form),
      });
      const data = await res.json();
      if (!res.ok) throw new Error(data.error || "Failed to save resume");
      setSuccess(data.message);
    } catch (err) {
      setSuccess(err.message);
    }
  };

  return (
    <div className="min-h-screen bg-gray-50">
      <nav className="bg-blue-600 text-white px-6 py-3 flex gap-4">
        <button className="btn" onClick={() => setPage("form")}>Resume Form</button>
        <button className="btn" onClick={() => setPage("pdf")}>Get PDF</button>
      </nav>
      <main className="py-8">
        {page === "form" ? (
          <>
            <ResumeForm onSubmit={handleSubmit} />
            {success && <div className="text-green-600 text-center mt-4">{success}</div>}
          </>
        ) : (
          <PdfPage />
        )}
      </main>
    </div>
  );
}

export default App;
