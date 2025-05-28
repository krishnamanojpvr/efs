import mongoose from 'mongoose';
import { v4 as uuidv4 } from 'uuid';

const InvoiceItemSchema = new mongoose.Schema({
  itemName: { type: String, required: true },
  quantity: { type: Number, required: true, min: 1 },
  price: { type: Number, required: true, min: 0.01 },
  amount: { type: Number, required: true, min: 0.01 },
});

const InvoiceBillSundrySchema = new mongoose.Schema({
  billSundryName: { type: String, required: true },
  amount: { type: Number, required: true }, // can be negative or positive
});

const InvoiceSchema = new mongoose.Schema({
  id: { type: String, required: true, unique: true, default: uuidv4 },
  date: { type: String, required: true },
  invoiceNumber: { type: Number, required: true, unique: true },
  customerName: { type: String, required: true },
  billingAddress: { type: String, required: true },
  shippingAddress: { type: String, required: true },
  GSTIN: { type: String, required: true },
  items: { type: [InvoiceItemSchema], required: true, validate: v => v.length > 0 },
  billSundrys: { type: [InvoiceBillSundrySchema], required: true },
  totalAmount: { type: Number, required: true },
});

export default mongoose.model('Invoice', InvoiceSchema);
