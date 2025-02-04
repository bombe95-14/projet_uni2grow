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
    public InvoiceItem save(InvoiceItem k) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void delete(InvoiceItem k) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void deleteAll(List<InvoiceItem> k) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public List<InvoiceItem> saveAll(List<InvoiceItem> k) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveAll'");
    }

    @Override
    public Optional<InvoiceItem> findById(Long identifier) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
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
