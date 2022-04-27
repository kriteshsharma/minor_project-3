package com.highradius.invoicesmanagement.dao;

import com.highradius.invoicesmanagement.model.Invoice;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/hrc?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root123";

    private static final String INSERT_INVOICE_SQL = "INSERT INTO UIT" + "  (Customer_Name,Customer_ID,Invoice_ID,Invoice_Amount,Due_Date,Predicted_Payment_Date,Notes) VALUES "
            + " (?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_INVOICE_BY_ID = "select S_No,Customer_Name,Customer_ID,Invoice_ID,Invoice_Amount,Due_Date,Predicted_Payment_Date,Notes from UIT where S_No =?";
    private static final String SELECT_ALL_INVOICES = "select * from UIT order by Due_Date desc";
    private static final String DELETE_INVOICE_SQL = "delete from UIT where S_No = ?;";
    private static final String UPDATE_INVOICE_SQL = "update UIT set Invoice_Amount = ?,Notes= ? where S_No = ?;";

    public InvoiceDAO() {
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertInvoice(Invoice invoice) throws SQLException {
        System.out.println(INSERT_INVOICE_SQL);
        //Customer_Name,Customer_ID,Invoice_ID,Invoice_Amount,Due_Date,Predicted_Payment_Date,Notes
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INVOICE_SQL)) {
            preparedStatement.setString(1, invoice.getCustomerName());
            preparedStatement.setString(2, invoice.getCustomerID());
            preparedStatement.setString(3, invoice.getInvoiceID());
            preparedStatement.setString(4, invoice.getAmount().toPlainString());
            preparedStatement.setDate(5, new java.sql.Date(invoice.getDueDate().getTime()));
            preparedStatement.setDate(6, new java.sql.Date(invoice.getPredictedPaymentDate().getTime()));
            preparedStatement.setString(7, invoice.getNotes());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Invoice selectInvoice(int id) {
        Invoice invoice = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INVOICE_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                invoice = createInvoice(rs);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return invoice;
    }

    private Invoice createInvoice(ResultSet rs) throws SQLException {
        //Customer_Name,Customer_ID,Invoice_ID,Invoice_Amount,Due_Date,Predicted_Payment_Date,Notes
        Invoice invoice = new Invoice();
        invoice.setId(rs.getInt("S_No"));
        invoice.setCustomerName(rs.getString("Customer_Name"));
        invoice.setCustomerID(rs.getString("Customer_ID"));
        invoice.setInvoiceID(rs.getString("Invoice_ID"));
        invoice.setAmount(new BigDecimal(rs.getString("Invoice_Amount")));
        invoice.setDueDate(new java.util.Date(rs.getDate("Due_Date").getTime()));
        invoice.setPredictedPaymentDate(new java.util.Date(rs.getDate("Predicted_Payment_Date").getTime()));
        invoice.setNotes(rs.getString("Notes"));
        return invoice;
    }

    public List<Invoice> selectAllInvoices() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Invoice> invoices = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_INVOICES);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                invoices.add(createInvoice(rs));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return invoices;
    }

    public boolean deleteInvoice(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_INVOICE_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateInvoice(Invoice invoice) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             //Invoice_Amount = ?,Notes= ?
             PreparedStatement statement = connection.prepareStatement(UPDATE_INVOICE_SQL);) {
            statement.setString(1, invoice.getAmount().toPlainString());
            statement.setString(2, invoice.getNotes());
            statement.setInt(3, invoice.getId());


            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


}
