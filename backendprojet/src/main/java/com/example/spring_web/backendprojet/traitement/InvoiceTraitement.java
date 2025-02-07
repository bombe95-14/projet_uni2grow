package com.example.spring_web.backendprojet.traitement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.spring_web.backendprojet.configuration.PDFGenerator;
import com.example.spring_web.backendprojet.dto.bodyrequest.CreateAndUpdateAddressDto;
import com.example.spring_web.backendprojet.dto.bodyrequest.CreateAndUpdateInvoiceDto;
import com.example.spring_web.backendprojet.entity.Address;
import com.example.spring_web.backendprojet.entity.Customer;
import com.example.spring_web.backendprojet.entity.Invoice;
import com.example.spring_web.backendprojet.entity.InvoiceItem;
import com.example.spring_web.backendprojet.service.AddressService;
import com.example.spring_web.backendprojet.service.CustomerService;
import com.example.spring_web.backendprojet.service.InvoiceItemService;
import com.example.spring_web.backendprojet.service.InvoiceService;

@Component
public class InvoiceTraitement { 
    
    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceItemService invoiceItemService;

    @Autowired
    private AddressService addressService;
   
    @Autowired
    private CustomerService customerService;

    @Autowired
    private PDFGenerator pdfGenerator;

    public InvoiceTraitement(PDFGenerator pdfGenerator) {
        this.pdfGenerator = pdfGenerator;
    }

     public ResponseEntity<?> getAllInvoice(){
        return ResponseEntity.ok( invoiceService.getAllElement() );
    }

    public ResponseEntity<?> getInvoicesWithPagination( int pageNumber, int pageSize ){
        return ResponseEntity.ok( invoiceService.getAddressesWithPagination(pageNumber, pageSize) );
    }

    public ResponseEntity<?> printOneInvoice( Long idInvoice ){
        Optional<Invoice> invoiceOptional = invoiceService.findById( idInvoice );
        if ( invoiceOptional.isEmpty() ) {
            return ResponseEntity.badRequest().body("Invoice not found");
        }

     try {
     byte[] zipContent = pdfGenerator.generatePDF(invoiceOptional.get());
     return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=invoices.zip")
      .body(zipContent);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
 
    }

     public ResponseEntity<?> createOneInvoice( CreateAndUpdateInvoiceDto createAndUpdateInvoiceDto ){

        if ( Objects.isNull( createAndUpdateInvoiceDto.getIdAddress() ) || Objects.isNull( createAndUpdateInvoiceDto.getIdCustomer() ) || 
           Objects.isNull(createAndUpdateInvoiceDto.getTotalAmount())  ) {
            return ResponseEntity.badRequest().body("Empty obligatories field");
        } 

        if ( createAndUpdateInvoiceDto.getTotalAmount()<=0 ) {
            return ResponseEntity.badRequest().body("Total must be greater than zero");
        }

        Optional<Address> addressOptional = addressService.findById(createAndUpdateInvoiceDto.getIdAddress());
        if ( addressOptional.isEmpty() ) {
            return ResponseEntity.badRequest().body("Address not found");
        }

        Optional<Customer> customerOptional = customerService.findById( createAndUpdateInvoiceDto.getIdCustomer() );
        if ( customerOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Customer not found");
        }

       Invoice invoiceSave = new Invoice(); 
       if ( createAndUpdateInvoiceDto.getInvoiceItemDtos().size()>0       ) {
        
            List<InvoiceItem> invoiceItemSave = new ArrayList<>();
            createAndUpdateInvoiceDto.getInvoiceItemDtos().forEach( invoiceItemDto -> invoiceItemSave.add( new InvoiceItem( invoiceItemDto )) );

            invoiceSave.setTotalAmount( createAndUpdateInvoiceDto.getTotalAmount() );
            invoiceSave.setInvoiceNumber( UUID.randomUUID().toString() );
           // invoiceSave.setInvoiceItems(invoiceItems);
            invoiceSave.setAddress(addressOptional.get());
            invoiceSave.setCustomer( customerOptional.get() );
            Invoice invoice = invoiceService.save( invoiceSave );  

            invoiceItemSave.forEach( 
                element -> {
                    element.setInvoice(invoice);
                    invoiceItemService.save(element);
                }
             );
          //  List<InvoiceItem> invoiceItems = invoiceItemService.saveAll( invoiceItemSave );
            return ResponseEntity
                   .status(201)
                   .body( invoice );
       } else {
            return ResponseEntity.badRequest()
                  .body( " Items is empty" );

       }
  
    }

   public ResponseEntity<?> updateOneInvoice( CreateAndUpdateInvoiceDto createAndUpdateInvoiceDto ){
   
        if ( Objects.isNull( createAndUpdateInvoiceDto.getIdAddress() ) || Objects.isNull( createAndUpdateInvoiceDto.getIdCustomer() ) || 
          Objects.isNull(createAndUpdateInvoiceDto.getIdInvoice() ) ||   Objects.isNull(createAndUpdateInvoiceDto.getTotalAmount()) ) {
            return ResponseEntity.badRequest().body("Empty obligatories field");
        }

        if ( createAndUpdateInvoiceDto.getTotalAmount()<=0 ) {
            return ResponseEntity.badRequest().body("Total must be greater than zero");
        }

        Optional<Address> addressOptional = addressService.findById(createAndUpdateInvoiceDto.getIdAddress());
        if ( addressOptional.isEmpty() ) {
            return ResponseEntity.badRequest().body("Address not found");
        }

        Optional<Customer> customerOptional = customerService.findById( createAndUpdateInvoiceDto.getIdCustomer() );
        if ( customerOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Customer not found");
        }

        Optional<Invoice> invoiceOptional = invoiceService.findById( createAndUpdateInvoiceDto.getIdInvoice() );
        if ( invoiceOptional.isEmpty() ) {
            return ResponseEntity.badRequest().body("Invoice not found");
        }

       Invoice invoiceSave = new Invoice(); 
       if ( createAndUpdateInvoiceDto.getInvoiceItemDtos().size()>0       ) {
        
            List<InvoiceItem> invoiceItemSave = new ArrayList<>();
            createAndUpdateInvoiceDto.getInvoiceItemDtos().forEach( invoiceItemDto -> invoiceItemSave.add( new InvoiceItem( invoiceItemDto )) );

            List<InvoiceItem> invoiceItems = invoiceItemService.saveAll( invoiceItemSave );
            invoiceSave.setTotalAmount( createAndUpdateInvoiceDto.getTotalAmount() );
            invoiceSave.setInvoiceNumber( UUID.randomUUID().toString() );
            invoiceSave.setInvoiceItems( invoiceItems );
            Invoice invoice = invoiceService.save( invoiceSave );  

            return ResponseEntity
                   .status(201)
                   .body( invoice );
       } else {
            return ResponseEntity
                  .status(201)
                  .body( " " );
                  
       }
  

     

    }
  
    public ResponseEntity<?> deleteOneInvoice( Long idInvoice){

        Optional <Invoice> invooiceOptional = invoiceService.findById( idInvoice );
        if ( invooiceOptional.isEmpty() ) {
            return ResponseEntity.badRequest().body("Address not found");
        }

        invoiceService.delete(invooiceOptional.get());

        return ResponseEntity.ok( " this address has been deleted " );

    }

}
