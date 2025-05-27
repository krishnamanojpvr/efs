const WebSocket = require("ws");
const server = new WebSocket.Server({ port: 4000 });
const Employees = require("./Schema.js");
const mongoose = require("mongoose");

mongoose
  .connect("mongodb://localhost:27017/efs")
  .then(() => {
    console.log("connected to mongodb");
  })
  .catch(() => {
    console.log("no mongo");
  });

server.on("connection", (socket) => {
  socket.on("connection", () => {
    console.log("hello");
  });
  socket.on("message", async (message) => {
    const data = message.toString().split(" ");
    if (data[0] === "INSERT" && data.length == 6) {
      const emp = new Employees({
        name: data[1],
        salary: data[2],
        role: data[3],
        department: data[4],
        experience: data[5],
      });
      await emp.save();
      socket.send(`Employee inserted successfully.ID: ${emp._id}`);
    } else if (data[0] === "RETRIEVE" && data.length == 1) {
      const employees = await Employees.find();
      employees.forEach((element) => {
        socket.send(
          `ID: ${element._id}, Name: ${element.name}, Salary: ${element.salary}, Role: ${element.role}, Department: ${element.department}, Experience: ${element.experience}`
        );
      });
    } else if (data[0] === "RETRIEVE_BY_DEPT" && data.length == 2) {
      const employees = await Employees.find({ department: data[1] });
      employees.forEach((element) => {
        socket.send(
          `ID: ${element._id}, Name: ${element.name}, Salary: ${element.salary}, Role: ${element.role}, Department: ${element.department}, Experience: ${element.experience}`
        );
      });
    } else {
      socket.send("Invalid command.");
    }
  });
  socket.on("close", () => {
    console.log("Connection closed");
  });
});
