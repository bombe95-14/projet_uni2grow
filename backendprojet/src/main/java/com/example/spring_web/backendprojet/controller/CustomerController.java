package com.example.spring_web.backendprojet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_web.backendprojet.dto.bodyrequest.AddressFilter;
import com.example.spring_web.backendprojet.dto.bodyrequest.CreateAndUpdateAddressDto;
import com.example.spring_web.backendprojet.dto.bodyrequest.CreateAndUpdateCustomerDto;
import com.example.spring_web.backendprojet.traitement.CustomerTraitement;

@RequestMapping("customer")
@RestController
public class CustomerController {
    
    @Autowired
    private CustomerTraitement customerTraitement;

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        return customerTraitement.getAllCustomers();
    }

    @GetMapping( value = "/pagination", params = { "page", "size" } )
    public ResponseEntity<?> getAddressesPaginated( @RequestParam("page") int page, @RequestParam("size") int size  ) {
        return customerTraitement.getCustomersWithPagination(page, size);
    }

    @PostMapping("/filter")
    public ResponseEntity<?> getAllAddressByFiltering( @RequestBody AddressFilter addressFilter) {
        return ResponseEntity.ok("Address");
    }

      @PostMapping("/add")
    public ResponseEntity<?> createOneCustomer( @RequestBody CreateAndUpdateCustomerDto createAndUpdateCustomerDto) {
        return customerTraitement.createCustomer(createAndUpdateCustomerDto);
    }

}
