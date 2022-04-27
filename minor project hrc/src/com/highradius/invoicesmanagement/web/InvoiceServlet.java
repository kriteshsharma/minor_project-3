package com.highradius.invoicesmanagement.web;

import com.highradius.invoicesmanagement.dao.InvoiceDAO;
import com.highradius.invoicesmanagement.model.Invoice;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * This controller servlet acts as a front controller for the application, handling all
 * requests from the user.
 */
@WebServlet("/")
public class InvoiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private InvoiceDAO invoiceDAO;

    public void init() {
        invoiceDAO = new InvoiceDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertInvoice(request, response);
                    break;
                case "/delete":
                    deleteInvoice(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateInvoice(request, response);
                    break;
                default:
                    listInvoice(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listInvoice(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Invoice> listInvoice = invoiceDAO.selectAllInvoices();
        request.setAttribute("listInvoice", listInvoice);
        RequestDispatcher dispatcher = request.getRequestDispatcher("invoice-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("invoice-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Invoice existingInvoice = invoiceDAO.selectInvoice(id);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        existingInvoice.setDueDateAsString(format.format(existingInvoice.getDueDate()));
        RequestDispatcher dispatcher = request.getRequestDispatcher("invoice-form.jsp");
        request.setAttribute("invoice", existingInvoice);
        dispatcher.forward(request, response);

    }

    private void insertInvoice(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        Invoice newInvoice = new Invoice();
        //invoice id
        String invoiceId = request.getParameter("invoiceID");
        newInvoice.setInvoiceID(invoiceId);
        //customer name
        String customerName = request.getParameter("customerName");
        newInvoice.setCustomerName(customerName);
        //customer name
        String customerId = request.getParameter("customerID");
        newInvoice.setCustomerID(customerId);
        //amount
        String amount = request.getParameter("amount");
        newInvoice.setAmount(new BigDecimal(amount));
        //due date
        try {
            String dueDate = request.getParameter("dueDate");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            newInvoice.setDueDate(format.parse(dueDate));
            newInvoice.setPredictedPaymentDate(format.parse(dueDate));
        } catch (ParseException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException(ex);
        }
        //due date
        //String predictedPaymentDate = request.getParameter("predictedPaymentDate");
        //newInvoice.setPredictedPaymentDate(new Date(predictedPaymentDate)); //TODO workround for a while ???
        //notes
        String notes = request.getParameter("notes");
        newInvoice.setNotes(notes);

        invoiceDAO.insertInvoice(newInvoice);
        response.sendRedirect("list");
    }

    private void updateInvoice(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String invoiceAmount = request.getParameter("amount");
        String notes = request.getParameter("notes");

        Invoice invoice = new Invoice();
        invoice.setNotes(notes);
        invoice.setAmount(new BigDecimal(invoiceAmount));
        invoice.setId(id);
        invoiceDAO.updateInvoice(invoice);
        response.sendRedirect("list");
    }

    private void deleteInvoice(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        invoiceDAO.deleteInvoice(id);
        response.sendRedirect("list");
    }
}
