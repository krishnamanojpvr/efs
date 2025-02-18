const WebSocket = require("ws");
const server = new WebSocket.Server({ port: 4000 });
const database = [];

server.on("connection", (socket) => {
  socket.on("connection", () => {
    console.log("hello");
  });
  socket.on("message", (message) => {
    const data = message.toString().split(" ");
    if (data[0] == "INSERT" && data.length == 3) {
      database.push({
        ID: database.length + 1,
        Name: data[1],
        Salary: data[2],
      });
      socket.send("Employee inserted successfully.");
    } else if (data[0] == "RETRIEVE" && data.length == 1) {
      database.forEach((element) => {
        socket.send(
          `ID:${element.ID}, Name: ${element.Name}, Salary: ${element.Salary}`
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
