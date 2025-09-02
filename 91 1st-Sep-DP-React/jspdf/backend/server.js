const express = require("express");
const mongoose = require("mongoose");
const cors = require("cors");
const dotenv = require("dotenv");

dotenv.config();

const app = express();
app.use(express.json());
app.use(cors());

mongoose.connect("mongodb://localhost:27017/resumeApp", {
  useNewUrlParser: true,
  useUnifiedTopology: true,
});

const educationSchema = new mongoose.Schema({
  institution: { type: String, required: true },
  graduationYear: { type: Number, required: true },
  cgpa: { type: String, required: true },
});

const resumeSchema = new mongoose.Schema({
  name: { type: String, required: true },
  phone: { type: String, required: true, unique: true },
  email: { type: String, required: true },
  github: { type: String, required: true },
  linkedin: { type: String, required: true },
  location: { type: String, required: true },
  summary: { type: String },

  education: { type: [educationSchema], required: true },

  technicalSkills: {
    type: Map,
    of: [String], // each category stores array of strings
    default: {
      "Programming Languages": [],
      "Web Technologies": [],
      Databases: [],
      "Frameworks/Libraries": [],
      "Tools/Platforms": [],
      "Operating Systems": [],
    },
  },

  experience: [
    {
      role: String,
      company: String,
      location: String,
      duration: String,
      details: [String],
    },
  ],

  projects: [
    {
      title: String,
      tech: String,
      description: String,
    },
  ],

  softSkills: [String],
  achievements: [String],
  certifications: [String],
});

const Resume = mongoose.model("Resume", resumeSchema);

app.post("/api/resume", async (req, res) => {
  try {
    const { phone } = req.body;
    const existing = await Resume.findOne({ phone });
    if (existing) {
      await Resume.updateOne({ phone }, req.body);
      return res.status(200).json({ message: "Resume updated successfully" });
    } else {
      const resume = new Resume(req.body);
      await resume.save();
      return res.status(201).json({ message: "Resume saved successfully" });
    }
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
});

app.get("/api/resume/:phone", async (req, res) => {
  try {
    const resume = await Resume.findOne({ phone: req.params.phone });
    if (!resume) return res.status(404).json({ error: "Resume not found" });
    res.json(resume);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

const PORT = process.env.PORT || 5000;
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
