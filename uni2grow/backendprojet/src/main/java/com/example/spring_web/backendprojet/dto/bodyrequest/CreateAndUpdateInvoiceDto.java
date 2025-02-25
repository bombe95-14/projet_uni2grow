package com.example.spring_web.backendprojet.dto.bodyrequest;

import java.util.ArrayList;
import java.util.List;

public class CreateAndUpdateInvoiceDto {
    
    private String invoiceNumber;
    private Double totalAmount;
    private Long idCustomer;
    private Long idAddress;
    private Long idInvoice;

   // private CreateAndUpdateCustomerDto createAndUpdateCustomerDto;


    private List<InvoiceItemDto> invoiceItemDtos = new ArrayList<>();

    public List<InvoiceItemDto> getInvoiceItemDtos() {
        return invoiceItemDtos;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public Long getIdAddress() {
        return idAddress;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }
    
    public Long getIdInvoice() {
        return idInvoice;
    }

/*     public CreateAndUpdateCustomerDto getCreateAndUpdateCustomerDto() {
        return createAndUpdateCustomerDto;
    } */

}
