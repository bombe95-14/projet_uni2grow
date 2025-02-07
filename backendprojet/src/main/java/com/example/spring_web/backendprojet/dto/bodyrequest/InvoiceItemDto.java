package com.example.spring_web.backendprojet.dto.bodyrequest;

public class  InvoiceItemDto {
    
    private String name;
    private int quantity;
    private double price;
    private double total;

    public double getTotal() {
        return total;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

}
