import React, { useState } from "react";

const initialEdu = [{ institution: "", graduationYear: "", cgpa: "" }];
const initialExp = [
  { role: "", company: "", location: "", duration: "", details: [""] },
];
const initialProj = [{ title: "", tech: "", description: "" }];
const initialTechnicalSkills = {
  "Programming Languages": [""],
  "Web Technologies": [""],
  Databases: [""],
  "Frameworks/Libraries": [""],
  "Tools/Platforms": [""],
  "Operating Systems": [""],
};

export default function ResumeForm({ onSubmit }) {
  const [form, setForm] = useState({
    name: "",
    phone: "",
    email: "",
    github: "",
    linkedin: "",
    location: "",
    summary: "",
    education: initialEdu,
    experience: initialExp,
    projects: initialProj,
    technicalSkills: initialTechnicalSkills,
    achievements: [""],
    certifications: [""],
    softSkills: [""],
  });
  const [error, setError] = useState("");

  // ========= General Handlers =========
  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleArrayChange = (field, idx, value) => {
    const updated = form[field].map((item, i) => (i === idx ? value : item));
    setForm({ ...form, [field]: updated });
  };

  const addArrayItem = (field, emptyValue = "") => {
    setForm({ ...form, [field]: [...form[field], emptyValue] });
  };

  const removeArrayItem = (field, idx) => {
    if (form[field].length === 1) return;
    setForm({ ...form, [field]: form[field].filter((_, i) => i !== idx) });
  };

  // ========= Education =========
  const handleEduChange = (idx, e) => {
    const updated = form.education.map((edu, i) =>
      i === idx ? { ...edu, [e.target.name]: e.target.value } : edu
    );
    setForm({ ...form, education: updated });
  };
  const addEdu = () => {
    setForm({
      ...form,
      education: [
        ...form.education,
        { institution: "", graduationYear: "", cgpa: "" },
      ],
    });
  };

  // ========= Work Experience =========
  const handleExpChange = (idx, e) => {
    const updated = form.experience.map((exp, i) =>
      i === idx ? { ...exp, [e.target.name]: e.target.value } : exp
    );
    setForm({ ...form, experience: updated });
  };
  const handleExpDetailChange = (expIdx, detailIdx, value) => {
    const updated = form.experience.map((exp, i) =>
      i === expIdx
        ? {
            ...exp,
            details: exp.details.map((d, j) => (j === detailIdx ? value : d)),
          }
        : exp
    );
    setForm({ ...form, experience: updated });
  };
  const addExpDetail = (expIdx) => {
    const updated = form.experience.map((exp, i) =>
      i === expIdx ? { ...exp, details: [...exp.details, ""] } : exp
    );
    setForm({ ...form, experience: updated });
  };
  const addExp = () => {
    setForm({
      ...form,
      experience: [
        ...form.experience,
        { role: "", company: "", location: "", duration: "", details: [""] },
      ],
    });
  };

  // ========= Skills ========
  const handleSkillChange = (category, idx, value) => {
    const updated = form.technicalSkills[category].map((s, i) =>
      i === idx ? value : s
    );
    setForm({
      ...form,
      technicalSkills: { ...form.technicalSkills, [category]: updated },
    });
  };

  const addSkill = (category) => {
    setForm({
      ...form,
      technicalSkills: {
        ...form.technicalSkills,
        [category]: [...form.technicalSkills[category], ""],
      },
    });
  };

  const removeSkill = (category, idx) => {
    if (form.technicalSkills[category].length === 1) return;
    setForm({
      ...form,
      technicalSkills: {
        ...form.technicalSkills,
        [category]: form.technicalSkills[category].filter((_, i) => i !== idx),
      },
    });
  };

  // ========= Projects =========
  const handleProjChange = (idx, e) => {
    const updated = form.projects.map((proj, i) =>
      i === idx ? { ...proj, [e.target.name]: e.target.value } : proj
    );
    setForm({ ...form, projects: updated });
  };
  const addProj = () => {
    setForm({
      ...form,
      projects: [...form.projects, { title: "", tech: "", description: "" }],
    });
  };

  // ========= Validation =========
  const validate = () => {
    for (const key in form) {
      if (!form[key] || (Array.isArray(form[key]) && !form[key].length))
        return false;
    }
    return true;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!validate()) {
      setError("All fields are required.");
      return;
    }
    setError("");
    onSubmit(form);
  };

  return (
    <form
      className="max-w-4xl mx-auto my-10 p-8 bg-gradient-to-br from-blue-50 via-white to-indigo-100 rounded-2xl shadow-lg space-y-6 border border-blue-200"
      onSubmit={handleSubmit}
    >
      <h2 className="text-3xl font-extrabold mb-2 text-blue-700 text-center tracking-wide">
        🚀 Create Your Mind-Blowing Resume!
      </h2>
      {error && (
        <div className="text-red-500 text-center font-medium">{error}</div>
      )}

      {/* Personal Info */}
      <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
        <input
          name="name"
          value={form.name}
          onChange={handleChange}
          placeholder="Full Name"
          className="input-style"
        />
        <input
          name="phone"
          value={form.phone}
          onChange={handleChange}
          placeholder="Phone Number"
          className="input-style"
        />
        <input
          name="email"
          value={form.email}
          onChange={handleChange}
          placeholder="Email Address"
          className="input-style"
        />
        <input
          name="github"
          value={form.github}
          onChange={handleChange}
          placeholder="GitHub URL"
          className="input-style"
        />
        <input
          name="linkedin"
          value={form.linkedin}
          onChange={handleChange}
          placeholder="LinkedIn URL"
          className="input-style"
        />
        <input
          name="location"
          value={form.location}
          onChange={handleChange}
          placeholder="Location"
          className="input-style"
        />
      </div>

      {/* Summary */}
      <div>
        <h3 className="font-semibold text-lg text-gray-700 mb-2">📝 Summary</h3>
        <textarea
          name="summary"
          value={form.summary}
          onChange={handleChange}
          placeholder="Write a short professional summary..."
          rows="3"
          className="input-style"
        />
      </div>

      {/* Education */}
      <div>
        <h3 className="font-semibold text-lg text-green-600 mb-2">
          🎓 Educational Qualifications
        </h3>
        {form.education.map((edu, idx) => (
          <div key={idx} className="flex flex-col md:flex-row gap-4 mb-2">
            <input
              name="institution"
              value={edu.institution}
              onChange={(e) => handleEduChange(idx, e)}
              placeholder="Institution"
              className="input-style"
            />
            <input
              name="graduationYear"
              value={edu.graduationYear}
              onChange={(e) => handleEduChange(idx, e)}
              placeholder="Year"
              className="input-style"
            />
            <input
              name="cgpa"
              value={edu.cgpa}
              onChange={(e) => handleEduChange(idx, e)}
              placeholder="CGPA/Percentage"
              className="input-style"
            />
          </div>
        ))}
        <button
          type="button"
          className="btn-style bg-green-500 hover:bg-green-600"
          onClick={addEdu}
        >
          + Add More Education
        </button>
      </div>

      {/* Work Experience */}
      <div>
        <h3 className="font-semibold text-lg text-blue-700 mb-2">
          💼 Work Experience
        </h3>
        {form.experience.map((exp, idx) => (
          <div
            key={idx}
            className="mb-4 p-3 border rounded-lg bg-white shadow-sm"
          >
            <input
              name="role"
              value={exp.role}
              onChange={(e) => handleExpChange(idx, e)}
              placeholder="Role"
              className="input-style mb-2"
            />
            <input
              name="company"
              value={exp.company}
              onChange={(e) => handleExpChange(idx, e)}
              placeholder="Company"
              className="input-style mb-2"
            />
            <input
              name="location"
              value={exp.location}
              onChange={(e) => handleExpChange(idx, e)}
              placeholder="Location"
              className="input-style mb-2"
            />
            <input
              name="duration"
              value={exp.duration}
              onChange={(e) => handleExpChange(idx, e)}
              placeholder="Duration"
              className="input-style mb-2"
            />
            <h4 className="font-medium mt-2">Details</h4>
            {exp.details.map((d, dIdx) => (
              <input
                key={dIdx}
                value={d}
                onChange={(e) =>
                  handleExpDetailChange(idx, dIdx, e.target.value)
                }
                placeholder="Achievement/Responsibility"
                className="input-style mb-1"
              />
            ))}
            <button
              type="button"
              className="btn-style bg-blue-500 hover:bg-blue-600 mt-2"
              onClick={() => addExpDetail(idx)}
            >
              + Add More Details
            </button>
          </div>
        ))}
        <button
          type="button"
          className="btn-style bg-indigo-500 hover:bg-indigo-600"
          onClick={addExp}
        >
          + Add More Experience
        </button>
      </div>

      {/* Projects */}
      <div>
        <h3 className="font-semibold text-lg text-yellow-600 mb-2">
          🚧 Projects
        </h3>
        {form.projects.map((proj, idx) => (
          <div
            key={idx}
            className="mb-4 p-3 border rounded-lg bg-white shadow-sm"
          >
            <input
              name="title"
              value={proj.title}
              onChange={(e) => handleProjChange(idx, e)}
              placeholder="Project Title"
              className="input-style mb-2"
            />
            <input
              name="tech"
              value={proj.tech}
              onChange={(e) => handleProjChange(idx, e)}
              placeholder="Tech Stack"
              className="input-style mb-2"
            />
            <textarea
              name="description"
              value={proj.description}
              onChange={(e) => handleProjChange(idx, e)}
              placeholder="Project Description"
              rows="3"
              className="input-style mb-2"
            />
          </div>
        ))}
        <button
          type="button"
          className="btn-style bg-yellow-500 hover:bg-yellow-600"
          onClick={addProj}
        >
          + Add More Projects
        </button>
      </div>

      {/* Technical Skills */}
      <div>
        <h3 className="font-semibold text-lg text-blue-700 mb-2">
          💡 Technical Skills
        </h3>
        {Object.keys(form.technicalSkills).map((category) => (
          <div key={category} className="mb-4">
            <h4 className="font-medium text-gray-700">{category}</h4>
            {form.technicalSkills[category].map((skill, idx) => (
              <div key={idx} className="flex gap-2 mb-2 items-center">
                <input
                  value={skill}
                  onChange={(e) =>
                    handleSkillChange(category, idx, e.target.value)
                  }
                  placeholder={`Enter ${category}`}
                  className="input-style"
                />
                {form.technicalSkills[category].length > 1 && (
                  <button
                    type="button"
                    className="btn-style bg-red-500 hover:bg-red-600 px-2 py-1 text-xs"
                    onClick={() => removeSkill(category, idx)}
                  >
                    Remove
                  </button>
                )}
              </div>
            ))}
            <button
              type="button"
              className="btn-style bg-blue-500 hover:bg-blue-600"
              onClick={() => addSkill(category)}
            >
              + Add More {category}
            </button>
          </div>
        ))}
      </div>

      {/* Soft Skills */}
      <div>
        <h3 className="font-semibold text-lg text-pink-600 mb-2">
          🌟 Soft Skills
        </h3>
        {form.softSkills.map((skill, idx) => (
          <div key={idx} className="flex gap-2 mb-2 items-center">
            <input
              value={skill}
              onChange={(e) =>
                handleArrayChange("softSkills", idx, e.target.value)
              }
              placeholder="Soft Skill"
              className="input-style"
            />
            {form.softSkills.length > 1 && (
              <button
                type="button"
                className="btn-style bg-red-500 hover:bg-red-600 px-2 py-1 text-xs"
                onClick={() => removeArrayItem("softSkills", idx)}
              >
                Remove
              </button>
            )}
          </div>
        ))}
        <button
          type="button"
          className="btn-style bg-pink-500 hover:bg-pink-600"
          onClick={() => addArrayItem("softSkills")}
        >
          + Add More Soft Skills
        </button>
      </div>

      {/* Achievements */}
      <div>
        <h3 className="font-semibold text-lg text-yellow-600 mb-2">
          🏆 Achievements
        </h3>
        {form.achievements.map((ach, idx) => (
          <div key={idx} className="flex gap-2 mb-2 items-center">
            <input
              value={ach}
              onChange={(e) =>
                handleArrayChange("achievements", idx, e.target.value)
              }
              placeholder="Achievement"
              className="input-style"
            />
            {form.achievements.length > 1 && (
              <button
                type="button"
                className="btn-style bg-red-500 hover:bg-red-600 px-2 py-1 text-xs"
                onClick={() => removeArrayItem("achievements", idx)}
              >
                Remove
              </button>
            )}
          </div>
        ))}
        <button
          type="button"
          className="btn-style bg-yellow-500 hover:bg-yellow-600"
          onClick={() => addArrayItem("achievements")}
        >
          + Add More Achievements
        </button>
      </div>

      {/* Certifications */}
      <div>
        <h3 className="font-semibold text-lg text-purple-700 mb-2">
          📜 Certifications
        </h3>
        {form.certifications.map((cert, idx) => (
          <div key={idx} className="flex gap-2 mb-2 items-center">
            <input
              value={cert}
              onChange={(e) =>
                handleArrayChange("certifications", idx, e.target.value)
              }
              placeholder="Certification"
              className="input-style"
            />
            {form.certifications.length > 1 && (
              <button
                type="button"
                className="btn-style bg-red-500 hover:bg-red-600 px-2 py-1 text-xs"
                onClick={() => removeArrayItem("certifications", idx)}
              >
                Remove
              </button>
            )}
          </div>
        ))}
        <button
          type="button"
          className="btn-style bg-purple-500 hover:bg-purple-600"
          onClick={() => addArrayItem("certifications")}
        >
          + Add More Certifications
        </button>
      </div>

      {/* Submit */}
      <button
        type="submit"
        className="btn-style bg-gradient-to-r from-blue-500 via-indigo-500 to-purple-500 hover:from-blue-600 hover:to-purple-600 text-white w-full text-xl py-3 mt-4 shadow-md"
      >
        Submit
      </button>

      {/* Tailwind Custom */}
      <style>
        {`
          .input-style {
            @apply w-full px-4 py-2 rounded-lg border border-gray-300 focus:ring-2 focus:ring-blue-400 outline-none transition duration-150 ease-in-out shadow-sm bg-white placeholder-gray-400;
          }
          .btn-style {
            @apply px-4 py-2 rounded-lg font-semibold text-white shadow transition duration-150 ease-in-out;
          }
        `}
      </style>
    </form>
  );
}
