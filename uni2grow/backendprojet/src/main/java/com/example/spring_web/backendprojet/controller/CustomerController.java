package com.example.spring_web.backendprojet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_web.backendprojet.dto.bodyrequest.AddressFilter;
import com.example.spring_web.backendprojet.dto.bodyrequest.CreateAndUpdateAddressDto;
import com.example.spring_web.backendprojet.dto.bodyrequest.CreateAndUpdateCustomerDto;
import com.example.spring_web.backendprojet.dto.bodyrequest.CustomerFilter;
import com.example.spring_web.backendprojet.traitement.CustomerTraitement;

@RequestMapping("customer")
@RestController
public class CustomerController {
    
    @Autowired
    private CustomerTraitement customerTraitement;

    @GetMapping
    @CrossOrigin( origins = "http://localhost:4200", methods = RequestMethod.GET )
    public ResponseEntity<?> getAllCustomers() {
        return customerTraitement.getAllCustomers();
    }

    @GetMapping( value = "/pagination", params = { "page", "size" } )
    @CrossOrigin( origins = "http://localhost:4200", methods = RequestMethod.GET )
    public ResponseEntity<?> getAddressesPaginated( @RequestParam("page") int page, @RequestParam("size") int size  ) {
        return customerTraitement.getCustomersWithPagination(page, size);
    }

    @PostMapping("/filter")
    @CrossOrigin( origins = "http://localhost:4200", methods = RequestMethod.POST )
    public ResponseEntity<?> getAllAddressByFiltering( @RequestBody CustomerFilter customerFilter ) {
        return ResponseEntity.ok( customerTraitement.getCustomerFiltering(customerFilter) );
    }

    @PostMapping("/add")
    @CrossOrigin( origins = "http://localhost:4200", methods = RequestMethod.POST )
    public ResponseEntity<?> createOneCustomer( @RequestBody CreateAndUpdateCustomerDto createAndUpdateCustomerDto) {
        return customerTraitement.createCustomer(createAndUpdateCustomerDto);
    }

    @PutMapping("/update")
    @CrossOrigin( origins = "http://localhost:4200", methods = RequestMethod.PUT )
    public ResponseEntity<?> updateOneCustomer( @RequestBody CreateAndUpdateCustomerDto createAndUpdateCustomerDto) {
        return customerTraitement.updateOneCustomer(createAndUpdateCustomerDto);
    }

    @DeleteMapping("/delete/{idCustomer}")
    @CrossOrigin( origins = "http://localhost:4200", methods = RequestMethod.DELETE )
    public ResponseEntity<?> deleteOneCustomer( @PathVariable("idCustomer") Long idCustomer) {
        return customerTraitement.deleteOneCustomer(idCustomer);
    }

}
