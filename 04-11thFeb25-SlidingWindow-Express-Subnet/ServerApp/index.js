const express = require("express");
const app = express();
const cors = require("cors");
app.use(express.json());
app.use(cors());
let orders = [
  {
    id: 1,
    customerName: "Azar",
    totalPrice: 150.0,
  },
];

app.post("/orders", (req, res) => {
  const order = req.body;
  order.id = orders.length + 1;
  orders.push(order);
  res.status(201).send(order);
});

app.get("/orders", (req, res) => {
  res.status(200).send(orders);
});

app.get("/orders/:id", (req, res) => {
  const id = req.params.id;
  const order = orders.find((order) => order.id == id);
  if (order) {
    res.status(200).send(order);
  } else {
    res.status(404).send();
  }
});

app.put("/orders/:id", (req, res) => {
  const id = req.params.id;
  const newOrder = req.body;
  const index = orders.findIndex((order) => order.id == id);
  if (index !== -1) {
    orders[index] = newOrder;
    res.status(200).send(newOrder);
  } else {
    res.status(404).send();
  }
});

app.delete("/orders/:id", (req, res) => {
  const id = req.params.id;
  orders = orders.filter((order) => order.id != id);
  res.status(200).send();
});

app.listen(4000, () => {
  console.log("Server is running on port 4000");
});
