package com.example.spring_web.backendprojet.entity;

import com.example.spring_web.backendprojet.dto.bodyrequest.InvoiceItemDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class InvoiceItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }
    private String name;
    private int quantity;
    private double price;
    private double total;
  
    @ManyToOne()
    @JoinColumn(name = "idInvoice")
    @JsonIgnore    
    private Invoice invoice; 

    public InvoiceItem() {
    }

    public InvoiceItem( InvoiceItemDto invoiceItemDto ){

        this.name = invoiceItemDto.getName();
        this.quantity = invoiceItemDto.getQuantity();
        this.price = invoiceItemDto.getPrice();
        this.total = invoiceItemDto.getTotal();

    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
