package com.example.spring_web.backendprojet.controller;

import javax.swing.text.html.FormSubmitEvent.MethodType;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;


import com.example.spring_web.backendprojet.dto.bodyrequest.CreateAndUpdateInvoiceDto;
import com.example.spring_web.backendprojet.dto.bodyrequest.InvoiceFilter;
import com.example.spring_web.backendprojet.traitement.InvoiceTraitement;

@RequestMapping("invoice")
@RestController
public class InvoiceController {
    
    @Autowired
    private InvoiceTraitement invoiceTraitement;

     @GetMapping
     @CrossOrigin( origins = "http://localhost:4200", methods = RequestMethod.GET )

    public ResponseEntity<?> getAllAddress() {
        return invoiceTraitement.getAllInvoice();
    }

    @GetMapping("/print/{idInvoice}")
    @CrossOrigin( origins = "http://localhost:4200", methods = RequestMethod.GET )

    public ResponseEntity<?> printOneInvoice( @PathVariable("idInvoice") Long idInvoice ) {
        return invoiceTraitement.printOneInvoice(idInvoice);
    }

    @PostMapping("/add")
    @CrossOrigin( origins = "http://localhost:4200", methods = RequestMethod.POST )
    public ResponseEntity<?> createOneInvoice( @RequestBody CreateAndUpdateInvoiceDto createAndUpdateInvoiceDto) {
        return invoiceTraitement.createOneInvoice(createAndUpdateInvoiceDto);
    }

    @PostMapping("/filter")
    @CrossOrigin( origins = "http://localhost:4200", methods = RequestMethod.POST )
    public ResponseEntity<?> getAllAddressByFiltering( @RequestBody InvoiceFilter invoiceFilter) {
        return ResponseEntity.ok("Address");
    }

    @PutMapping("/update")
    @CrossOrigin( origins = "http://localhost:4200", methods = RequestMethod.PUT )
    public ResponseEntity<?> updateOneInvoice(@RequestBody CreateAndUpdateInvoiceDto createAndUpdateInvoiceDto) {
        return invoiceTraitement.updateOneInvoice(createAndUpdateInvoiceDto);
    }

    @GetMapping( value = "/pagination", params = { "page", "size" } )
    @CrossOrigin( origins = "http://localhost:4200", methods = RequestMethod.GET )

    public ResponseEntity<?> getAddressesPaginated( @RequestParam("page") int page, @RequestParam("size") int size  ) {
        return invoiceTraitement.getInvoicesWithPagination(page, size);
    }

    @DeleteMapping("/delete/{idInvoice}")  
    @CrossOrigin( origins = "http://localhost:4200", methods = RequestMethod.DELETE )

    public ResponseEntity<?> deleteOneInvoice( @PathVariable("idInvoice") Long idInvoice) {
        return invoiceTraitement.deleteOneInvoice(idInvoice);
    }

}
