import express from 'express';
import Invoice from '../models/Invoice.js';

const router = express.Router();

// Helper: get next invoice number
async function getNextInvoiceNumber() {
  const last = await Invoice.findOne().sort({ invoiceNumber: -1 });
  return last ? last.invoiceNumber + 1 : 1;
}

// Create Invoice
router.post('/', async (req, res) => {
  try {
    const data = req.body;
    console.log(data)
    // Auto-increment invoice number
    data.invoiceNumber = await getNextInvoiceNumber();
    // Validate items and bill sundries
    if (!data.items || data.items.length === 0) return res.status(400).json({ error: 'At least one item required.' });
    data.items = data.items.map(item => ({
      ...item,
      amount: Number(item.quantity) * Number(item.price)
    }));
    if (data.items.some(item => item.amount <= 0)) return res.status(400).json({ error: 'Item amount must be > 0.' });
    // Bill sundries can be negative or positive
    // Calculate total
    const itemsTotal = data.items.reduce((sum, i) => sum + i.amount, 0);
    const billSundryTotal = (data.billSundrys || []).reduce((sum, b) => sum + Number(b.amount), 0);
    data.totalAmount = itemsTotal + billSundryTotal;
    // Date must not be backdated
    if (new Date(data.date) < new Date().setHours(0,0,0,0)) return res.status(400).json({ error: 'Backdated entries not allowed.' });
    const invoice = new Invoice(data);
    await invoice.save();
    res.status(201).json(invoice);
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
});

// Get all invoices
router.get('/', async (req, res) => {
  const invoices = await Invoice.find();
  res.json(invoices);
});

// Get invoice by ID
router.get('/:id', async (req, res) => {
  try {
    const invoice = await Invoice.findOne({id:req.params.id});
    if (!invoice) return res.status(404).json({ error: 'Not found' });
    res.json(invoice);
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
});

// Update invoice
router.put('/:id', async (req, res) => {
  try {
    const data = req.body;
    // Validate items and bill sundries
    if (!data.items || data.items.length === 0) return res.status(400).json({ error: 'At least one item required.' });
    data.items = data.items.map(item => ({
      ...item,
      amount: Number(item.quantity) * Number(item.price)
    }));
    if (data.items.some(item => item.amount <= 0)) return res.status(400).json({ error: 'Item amount must be > 0.' });
    const itemsTotal = data.items.reduce((sum, i) => sum + i.amount, 0);
    const billSundryTotal = (data.billSundrys || []).reduce((sum, b) => sum + Number(b.amount), 0);
    data.totalAmount = itemsTotal + billSundryTotal;
    // Date must not be backdated
    if (new Date(data.date) < new Date().setHours(0,0,0,0)) return res.status(400).json({ error: 'Backdated entries not allowed.' });
    const invoice = await Invoice.findOneAndUpdate({id:req.params.id}, data, { new: true });
    if (!invoice) return res.status(404).json({ error: 'Not found' });
    res.json(invoice);
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
});

// Delete invoice
router.delete('/:id', async (req, res) => {
  try {
    const invoice = await Invoice.findOneAndDelete({id:req.params.id});
    if (!invoice) return res.status(404).json({ error: 'Not found' });
    res.json({ message: 'Deleted' });
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
});

export default router;
