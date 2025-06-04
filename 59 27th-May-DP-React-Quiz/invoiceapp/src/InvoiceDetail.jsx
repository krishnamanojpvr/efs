import React, { useState, useEffect } from 'react';
import {
  Box,
  Button,
  Grid,
  TextField,
  Typography,
  IconButton,
  Paper
} from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import AddIcon from '@mui/icons-material/Add';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';

function InvoiceDetail() {
  const navigate = useNavigate();
  const { id } = useParams();
  const isUpdate = Boolean(id);

  // Loading and error state
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  // Form state
  const [form, setForm] = useState({
    id: '',
    date: '',
    invoiceNumber: '',
    customerName: '',
    billingAddress: '',
    shippingAddress: '',
    GSTIN: '',
    items: [{ itemName: '', quantity: 1, price: 0, amount: 0 }],
    billSundrys: [{ billSundryName: '', amount: 0 }],
    totalAmount: 0
  });

  // Fetch invoice if updating
  useEffect(() => {
    if (isUpdate) {
      setLoading(true);
      axios.get(`/api/invoices/${id}`)
        .then(res => setForm({
          id: res.data.id,
          date: res.data.date,
          invoiceNumber: res.data.invoiceNumber,
          customerName: res.data.customerName,
          billingAddress: res.data.billingAddress,
          shippingAddress: res.data.shippingAddress,
          GSTIN: res.data.GSTIN,
          items: res.data.items,
          billSundrys: res.data.billSundrys,
          totalAmount: res.data.totalAmount
        }))
        .catch(err => setError(err.response?.data?.message || err.message))
        .finally(() => setLoading(false));
    } else {
      // Fetch next invoice number
      setLoading(true);
      axios.get('/api/invoices/next-number')
        .then(res => setForm(f => ({ ...f, invoiceNumber: res.data.nextNumber })))
        .catch(() => {})
        .finally(() => setLoading(false));
    }
  }, [id, isUpdate]);

  // Handlers for form fields
  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  // Items logic
  const handleItemChange = (idx, field, value) => {
    const items = [...form.items];
    items[idx][field] = value;
    if (field === 'quantity' || field === 'price') {
      items[idx].amount = (parseFloat(items[idx].quantity) || 0) * (parseFloat(items[idx].price) || 0);
    }
    setForm({ ...form, items });
    recalculateTotal(items, form.billSundrys);
  };

  const addItem = () => {
    setForm({ ...form, items: [...form.items, { itemName: '', quantity: 1, price: 0, amount: 0 }] });
  };

  const removeItem = (idx) => {
    if (form.items.length === 1) return;
    const items = form.items.filter((_, i) => i !== idx);
    setForm({ ...form, items });
    recalculateTotal(items, form.billSundrys);
  };

  // Bill Sundry logic
  const handleBillSundryChange = (idx, field, value) => {
    const billSundrys = [...form.billSundrys];
    billSundrys[idx][field] = value;
    setForm({ ...form, billSundrys });
    recalculateTotal(form.items, billSundrys);
  };

  const addBillSundry = () => {
    setForm({ ...form, billSundrys: [...form.billSundrys, { billSundryName: '', amount: 0 }] });
  };

  const removeBillSundry = (idx) => {
    if (form.billSundrys.length === 1) return;
    const billSundrys = form.billSundrys.filter((_, i) => i !== idx);
    setForm({ ...form, billSundrys });
    recalculateTotal(form.items, billSundrys);
  };

  // Recalculate total
  const recalculateTotal = (items, billSundrys) => {
    const itemsTotal = items.reduce((sum, item) => sum + (parseFloat(item.amount) || 0), 0);
    const billSundryTotal = billSundrys.reduce((sum, bs) => sum + (parseFloat(bs.amount) || 0), 0);
    setForm((prev) => ({ ...prev, totalAmount: itemsTotal + billSundryTotal }));
  };

  // Validation
  const validate = () => {
    if (!form.date || !form.customerName || !form.billingAddress || !form.shippingAddress || !form.GSTIN) return 'All fields are mandatory.';
    if (new Date(form.date) < new Date(new Date().toISOString().split('T')[0])) return 'Backdated entries are not allowed.';
    if (!form.items.length) return 'At least one item is required.';
    for (const item of form.items) {
      if (!item.itemName || item.quantity <= 0 || item.price <= 0) return 'Item fields are required and price/quantity must be > 0.';
      if (item.amount !== item.quantity * item.price) return 'Item amount must be quantity × price.';
    }
    if (form.totalAmount !== form.items.reduce((sum, i) => sum + (parseFloat(i.amount) || 0), 0) + form.billSundrys.reduce((sum, b) => sum + (parseFloat(b.amount) || 0), 0)) return 'Total amount calculation error.';
    return null;
  };

  // Form submit handlers
  const handleSave = async () => {
    const validationError = validate();
    if (validationError) {
      setError(validationError);
      return;
    }
    setLoading(true);
    setError(null);
    try {
      if (isUpdate) {
        await axios.put(`/api/invoices/${id}`, form);
      } else {
        await axios.post('/api/invoices', form);
      }
      navigate('/');
    } catch (err) {
      setError(err.response?.data?.message || err.message);
    } finally {
      setLoading(false);
    }
  };
  const handleDelete = async () => {
    if (!window.confirm('Are you sure you want to delete this invoice?')) return;
    setLoading(true);
    setError(null);
    try {
      await axios.delete(`/api/invoices/${id}`);
      navigate('/');
    } catch (err) {
      setError(err.response?.data?.message || err.message);
    } finally {
      setLoading(false);
    }
  };
  const handleCancel = () => {
    navigate('/');
  };

  return (
    <Box component={Paper} p={3}>
      <Typography variant="h5" mb={2}>{isUpdate ? 'Update Invoice' : 'Create Invoice'}</Typography>
      {loading && <Typography color="primary">Loading...</Typography>}
      {error && <Typography color="error">{error}</Typography>}
      <Grid container spacing={2}>
        <Grid item xs={12} sm={6}>
          <TextField label="Date" name="date" type="date" value={form.date} onChange={handleChange} fullWidth InputLabelProps={{ shrink: true }} required />
        </Grid>
        
        <Grid item xs={12} sm={6}>
          <TextField label="Customer Name" name="customerName" value={form.customerName} onChange={handleChange} fullWidth required />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField label="GSTIN" name="GSTIN" value={form.GSTIN} onChange={handleChange} fullWidth required />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField label="Billing Address" name="billingAddress" value={form.billingAddress} onChange={handleChange} fullWidth required />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField label="Shipping Address" name="shippingAddress" value={form.shippingAddress} onChange={handleChange} fullWidth required />
        </Grid>
      </Grid>
      <Box mt={3}>
        <Typography variant="h6">Items</Typography>
        {form.items.map((item, idx) => (
          <Grid container spacing={1} alignItems="center" key={idx} mb={1}>
            <Grid item xs={3}>
              <TextField label="Item Name" value={item.itemName} onChange={e => handleItemChange(idx, 'itemName', e.target.value)} fullWidth required />
            </Grid>
            <Grid item xs={2}>
              <TextField label="Qty" type="number" value={item.quantity} onChange={e => handleItemChange(idx, 'quantity', e.target.value)} fullWidth required inputProps={{ min: 1 }} />
            </Grid>
            <Grid item xs={2}>
              <TextField label="Price" type="number" value={item.price} onChange={e => handleItemChange(idx, 'price', e.target.value)} fullWidth required inputProps={{ min: 0.01, step: 0.01 }} />
            </Grid>
            <Grid item xs={2}>
              <TextField label="Amount" value={item.amount} fullWidth disabled />
            </Grid>
            <Grid item xs={2}>
              <IconButton onClick={() => removeItem(idx)} disabled={form.items.length === 1}><DeleteIcon /></IconButton>
            </Grid>
          </Grid>
        ))}
        <Button startIcon={<AddIcon />} onClick={addItem} sx={{ mt: 1 }}>Add Item</Button>
      </Box>
      <Box mt={3}>
        <Typography variant="h6">Bill Sundries</Typography>
        {form.billSundrys.map((bs, idx) => (
          <Grid container spacing={1} alignItems="center" key={idx} mb={1}>
            <Grid item xs={4}>
              <TextField label="Bill Sundry Name" value={bs.billSundryName} onChange={e => handleBillSundryChange(idx, 'billSundryName', e.target.value)} fullWidth required />
            </Grid>
            <Grid item xs={4}>
              <TextField label="Amount" type="number" value={bs.amount} onChange={e => handleBillSundryChange(idx, 'amount', e.target.value)} fullWidth required />
            </Grid>
            <Grid item xs={2}>
              <IconButton onClick={() => removeBillSundry(idx)} disabled={form.billSundrys.length === 1}><DeleteIcon /></IconButton>
            </Grid>
          </Grid>
        ))}
        <Button startIcon={<AddIcon />} onClick={addBillSundry} sx={{ mt: 1 }}>Add Bill Sundry</Button>
      </Box>
      <Box mt={3}>
        <Typography variant="h6">Total Amount: {form.totalAmount}</Typography>
      </Box>
      <Box mt={3} display="flex" gap={2}>
        <Button variant="outlined" onClick={handleCancel}>Cancel</Button>
        {isUpdate && <Button variant="contained" color="error" onClick={handleDelete}>Delete</Button>}
        <Button variant="contained" color="primary" onClick={handleSave}>Save</Button>
      </Box>
    </Box>
  );
}

export default InvoiceDetail;
