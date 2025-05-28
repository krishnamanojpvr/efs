# Invoice Management Backend (Node.js + Express + MongoDB)

This backend provides RESTful APIs for managing invoices, line items, and bill sundries.

## Features
- CRUD for invoices
- MongoDB models for Invoice, InvoiceItem, InvoiceBillSundry
- Business rule validations

## Setup
1. Install dependencies:
   ```sh
   npm install
   ```
2. Create a `.env` file with your MongoDB URI:
   ```env
   MONGODB_URI=mongodb://localhost:27017/invoiceapp
   PORT=5000
   ```
3. Start the server:
   ```sh
   node index.js
   ```

## API Endpoints
- `POST /invoices` - Create invoice
- `GET /invoices` - List all invoices
- `GET /invoices/:id` - Get invoice by ID
- `PUT /invoices/:id` - Update invoice
- `DELETE /invoices/:id` - Delete invoice

See code for details on request/response formats.
