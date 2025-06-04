import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import {
  Box,
  Button,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  TablePagination,
  Typography
} from '@mui/material';

function InvoiceList() {
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(5);
  const [invoices, setInvoices] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    setLoading(true);
    setError(null);
    axios.get('/api/invoices')
      .then(res => {
        // Ensure invoices is always an array
        setInvoices(Array.isArray(res.data) ? res.data : []);
      })
      .catch(err => setError(err.response?.data?.message || err.message))
      .finally(() => setLoading(false));
  }, []);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  return (
    <Box>
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={2}>
        <Typography variant="h5">Invoices</Typography>
        <Button variant="contained" color="primary" onClick={() => navigate('/invoice/new')}>
          Add
        </Button>
      </Box>
      {loading && <Typography color="primary">Loading...</Typography>}
      {error && <Typography color="error">{error}</Typography>}
      <Paper>
        <TableContainer>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Date</TableCell>
                <TableCell>Invoice Number</TableCell>
                <TableCell>Customer Name</TableCell>
                <TableCell>Total Amount</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {Array.isArray(invoices) && invoices.length > 0 ? (
                invoices.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((invoice) => (
                  <TableRow
                    key={invoice.id}
                    hover
                    style={{ cursor: 'pointer' }}
                    onClick={() => navigate(`/invoice/${invoice.id}`)}
                  >
                    <TableCell>{invoice.date}</TableCell>
                    <TableCell>{invoice.invoiceNumber}</TableCell>
                    <TableCell>{invoice.customerName}</TableCell>
                    <TableCell>{invoice.totalAmount}</TableCell>
                  </TableRow>
                ))
              ) : (
                <TableRow>
                  <TableCell colSpan={4} align="center">No invoices found.</TableCell>
                </TableRow>
              )}
            </TableBody>
          </Table>
        </TableContainer>
        <TablePagination
          component="div"
          count={invoices.length}
          page={page}
          onPageChange={handleChangePage}
          rowsPerPage={rowsPerPage}
          onRowsPerPageChange={handleChangeRowsPerPage}
          rowsPerPageOptions={[5, 10, 25]}
        />
      </Paper>
    </Box>
  );
}

export default InvoiceList;
