package com.example.spring_web.backendprojet.dto.bodyrequest;

import java.util.ArrayList;
import java.util.List;

public class CreateAndUpdateInvoiceDto {
    
    private String invoiceNumber;
    private double totalAmount;
    private CreateAndUpdateCustomerDto createAndUpdateCustomerDto;

    private List<InvoiceItemDto> invoiceItemDtos = new ArrayList<>();

    public List<InvoiceItemDto> getInvoiceItemDtos() {
        return invoiceItemDtos;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public CreateAndUpdateCustomerDto getCreateAndUpdateCustomerDto() {
        return createAndUpdateCustomerDto;
    }

}
