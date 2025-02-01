
package com.example.spring_web.backendprojet.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Invoice {
    

    private String invoiceNumber;
    private double totalAmount;

    @ManyToOne
    private Customer customer;

    @OneToMany
    private Set<InvoiceItem> invoiceItems = new HashSet<>();


    -  items : a list of  InvoiceItem  objects representing the items included in the invoice 
    -  billingAddress : an Address  object representing the billing address associated with the invoice 


}