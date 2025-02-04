package com.example.spring_web.backendprojet.traitement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.spring_web.backendprojet.service.AddressService;
import com.example.spring_web.backendprojet.service.CustomerService;
import com.example.spring_web.backendprojet.service.InvoiceService;

@Component
public class InvoiceTraitement {
    
    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private AddressService addressService;
   
    @Autowired
    private CustomerService customerService;


}
