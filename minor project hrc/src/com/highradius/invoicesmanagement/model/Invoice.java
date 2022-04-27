package com.highradius.invoicesmanagement.model;

import java.math.BigDecimal;
import java.util.Date;

public class Invoice {

    private int id; //primary key
    private String customerName;
    private String customerID;
    private String invoiceID;
    private BigDecimal amount;
    private Date dueDate;
    private String dueDateAsString;
    private Date predictedPaymentDate;
    private String notes;

    public Invoice() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPredictedPaymentDate() {
        return predictedPaymentDate;
    }

    public void setPredictedPaymentDate(Date predictedPaymentDate) {
        this.predictedPaymentDate = predictedPaymentDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDueDateAsString() {
        return dueDateAsString;
    }

    public void setDueDateAsString(String dueDateAsString) {
        this.dueDateAsString = dueDateAsString;
    }
}
