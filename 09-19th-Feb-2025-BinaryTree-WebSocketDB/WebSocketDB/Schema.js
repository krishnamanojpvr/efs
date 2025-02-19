const mongoose = require('mongoose');
const sequence = require('mongoose-sequence')(mongoose);
const employeeSchema = new mongoose.Schema({
    _id : Number,
    name: String,
    salary: Number,
    role: String,
    department: String,
    experience: Number
});

employeeSchema.plugin(sequence,{inc_field : '_id'});
module.exports = mongoose.model('Employee',employeeSchema);