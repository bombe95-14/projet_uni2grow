package com.example.spring_web.backendprojet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.spring_web.backendprojet.entity.InvoiceItem;
import com.example.spring_web.backendprojet.repository.InvoiceItemRepository;

@Service
public class InvoiceItemService implements GlobalServiceCrud<InvoiceItem> {

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    @Override
    public InvoiceItem save(InvoiceItem invoiceItem) {
        // TODO Auto-generated method stub
        return invoiceItemRepository.save(invoiceItem);
    }

    @Override
    public void delete(InvoiceItem invoiceItem) {
        // TODO Auto-generated method stub
        invoiceItemRepository.delete(invoiceItem);
    }

    @Override
    public void deleteAll(List<InvoiceItem> k) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public List<InvoiceItem> saveAll(List<InvoiceItem> invoiceItems) {
        // TODO Auto-generated method stub
        return invoiceItemRepository.saveAll(invoiceItems);
    }

    @Override
    public Optional<InvoiceItem> findById(Long identifier) {
        // TODO Auto-generated method stub
        return invoiceItemRepository.findById(identifier);
    }

    public void emptyTableAddress() {
        invoiceItemRepository.deleteAll();
    }

    @Override
    public List<InvoiceItem> getAllElement() {
        // TODO Auto-generated method stub
        return invoiceItemRepository.findAll();
    }
    
}
