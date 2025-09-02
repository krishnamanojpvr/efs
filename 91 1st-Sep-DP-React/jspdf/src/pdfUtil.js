import jsPDF from "jspdf";

export function generateResumePdf(resume) {
  const doc = new jsPDF({ unit: "mm", format: "a4" });
  let y = 20;

  // ===== HEADER =====
  doc.setFont("helvetica", "bold");
  doc.setFontSize(20);
  doc.text(resume.name.toUpperCase(), 15, y);
  y += 7;

  doc.setFont("helvetica", "normal");
  doc.setFontSize(11);
  doc.text(
    `Phone: ${resume.phone}   E-mail: ${resume.email}   Location: ${resume.location}`,
    15,
    y
  );
  y += 5;
  doc.text(`LinkedIn: ${resume.linkedin}   GitHub: ${resume.github}`, 15, y);
  y += 9;

  // ===== SECTION FUNCTION =====
  function section(title) {
    doc.setFont("helvetica", "bold");
    doc.setFontSize(12);
    doc.text(title.toUpperCase() + " :", 15, y);
    y += 4;

    // Divider line
    doc.setDrawColor(180, 180, 180);
    doc.setLineWidth(0.3);
    doc.line(15, y, 195, y);
    y += 5;

    doc.setFont("helvetica", "normal");
    doc.setFontSize(11);
  }

  // ===== SUMMARY =====
  if (resume.summary) {
    section("Summary");
    let summary = doc.splitTextToSize(resume.summary, 175);
    doc.text(summary, 20, y);
    y += summary.length * 5 + 3; // tighter spacing
  }

  // ===== TECHNICAL SKILLS =====
  section("Technical Skills");
  Object.keys(resume.technicalSkills || {}).forEach((cat) => {
    const skillsArr = (resume.technicalSkills[cat] || []).filter(Boolean);
    if (skillsArr.length > 0) {
      doc.setFont("helvetica", "bold");
      doc.text(`${cat}:`, 20, y);
      doc.setFont("helvetica", "normal");

      let skills = doc.splitTextToSize(skillsArr.join(", "), 110);
      doc.text(skills, 70, y); // align neatly
      y += skills.length * 5;
    }
  });
  y += 3;

  // ===== WORK EXPERIENCE =====
  if (resume.experience?.length) {
    section("Work Experience");
    resume.experience.forEach((exp) => {
      if (!exp.role && !exp.company) return;
      doc.setFont("helvetica", "bold");
      doc.text(
        `${exp.role} | ${exp.company} | ${exp.location} | ${exp.duration}`,
        20,
        y
      );
      y += 5;
      doc.setFont("helvetica", "normal");
      (exp.details || []).forEach((d) => {
        if (!d) return;
        let wrapped = doc.splitTextToSize(`• ${d}`, 170);
        doc.text(wrapped, 25, y);
        y += wrapped.length * 5;
      });
      y += 2;
    });
  }

  // ===== PROJECTS =====
  if (resume.projects?.length) {
    section("Projects");
    resume.projects.forEach((proj) => {
      if (!proj.title) return;
      doc.setFont("helvetica", "bold");
      doc.text(`${proj.title}${proj.tech ? " (" + proj.tech + ")" : ""}`, 20, y);
      y += 5;
      doc.setFont("helvetica", "normal");
      if (proj.description) {
        let desc = doc.splitTextToSize(proj.description, 170);
        doc.text(desc, 25, y);
        y += desc.length * 5 + 2;
      }
    });
  }

  // ===== EDUCATION =====
  if (resume.education?.length) {
    section("Educational Qualifications");
    resume.education.forEach((edu) => {
      if (!edu.institution) return;
      doc.setFont("helvetica", "bold");
      doc.text(`${edu.institution}`, 20, y);
      doc.setFont("helvetica", "normal");
      doc.text(
        `${edu.graduationYear ? edu.graduationYear + " | " : ""}${
          edu.cgpa ? "CGPA/Marks: " + edu.cgpa : ""
        }`,
        25,
        y + 5
      );
      y += 10;
    });
  }

  // ===== SOFT SKILLS =====
  if (resume.softSkills?.length) {
    section("Soft Skills");
    resume.softSkills.forEach((skill) => {
      if (!skill) return;
      doc.text(`• ${skill}`, 20, y);
      y += 5;
    });
    y += 3;
  }

  // ===== CERTIFICATIONS =====
  if (resume.certifications?.length) {
    section("Courses / Certifications");
    resume.certifications.forEach((cert) => {
      if (!cert) return;
      let wrapped = doc.splitTextToSize(`• ${cert}`, 170);
      doc.text(wrapped, 20, y);
      y += wrapped.length * 5;
    });
    y += 3;
  }

  // ===== ACHIEVEMENTS =====
  if (resume.achievements?.length) {
    section("Achievements");
    resume.achievements.forEach((ach) => {
      if (!ach) return;
      let wrapped = doc.splitTextToSize(`• ${ach}`, 170);
      doc.text(wrapped, 20, y);
      y += wrapped.length * 5;
    });
  }

  return doc;
}
