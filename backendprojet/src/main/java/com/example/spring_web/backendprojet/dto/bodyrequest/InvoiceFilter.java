package com.example.spring_web.backendprojet.dto.bodyrequest;

import java.util.Date;

public class InvoiceFilter {
    
    private String invoiceNumber;
    private Date startDate;
    private Date endDate;
    private String clientName;

    public Date getEndDate() {
        return endDate;
    }

    public String getClientName() {
        return clientName;
    }

    public Date getStartDate() {
        return startDate;
    }


    public String getInvoiceNumber() {
        return invoiceNumber;
    }
 
}
