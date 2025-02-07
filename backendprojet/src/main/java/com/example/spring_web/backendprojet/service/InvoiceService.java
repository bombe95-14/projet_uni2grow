package com.example.spring_web.backendprojet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.spring_web.backendprojet.entity.Address;
import com.example.spring_web.backendprojet.entity.Invoice;
import com.example.spring_web.backendprojet.repository.InvoiceRepository;

@Service
public class InvoiceService implements GlobalServiceCrud<Invoice> {

    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save( invoice);
    }

    @Override
    public void delete(Invoice invoice) {
        invoiceRepository.delete( invoice);
    }

    @Override
    public void deleteAll(List<Invoice> invoices) {
        invoiceRepository.deleteAll( invoices );
    }

    @Override
    public List<Invoice> saveAll(List<Invoice> invoices) {
        return invoiceRepository.saveAll(invoices);
    }

    @Override
    public Optional<Invoice> findById(Long idInvoice) {
        return invoiceRepository.findById(idInvoice);
    }
    
    public void emptyTableAddress() {
        invoiceRepository.deleteAll();
    }

    @Override
    public List<Invoice> getAllElement() {
        // TODO Auto-generated method stub
        List<Invoice> invoices = invoiceRepository.findAll();
        System.out.println( "longueur  est de   " + invoices.get(0).getInvoiceItems().size() );
        return invoices;
    }

     public List<Invoice> getAddressesWithPagination( int pageNumber, int pageSize ) {
        // TODO Auto-generated method stub
        return (List<Invoice>) invoiceRepository.findAll( PageRequest.of(pageNumber, pageSize) ).getContent();
    }

    public long countInvoices(){
        return invoiceRepository.count();
    }
}
