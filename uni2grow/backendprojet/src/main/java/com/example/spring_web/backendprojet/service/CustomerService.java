package com.example.spring_web.backendprojet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.spring_web.backendprojet.entity.Customer;
import com.example.spring_web.backendprojet.repository.CustomerRepository;


@Service
public class CustomerService implements GlobalServiceCrud<Customer> {

    @Autowired
    private CustomerRepository customerRepository;  
    
    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public void deleteAll(List<Customer> customers) {
        customerRepository.deleteAll(customers);
    }

    @Override
    public List<Customer> saveAll(List<Customer> customers) {
        return customerRepository.saveAll(customers);
    }

    @Override
    public Optional<Customer> findById(Long idCustomer) {
        return customerRepository.findById(idCustomer);
    }
    
    public void emptyTableCustomer() {
        customerRepository.deleteAll();
    }

    @Override
    public List<Customer> getAllElement() {
        return customerRepository.findAll();
    }

    public List<Customer> getCustomersWithPagination( int pageNumber, int pageSize ){
        return (List<Customer>) customerRepository.findAll( PageRequest.of(pageNumber, pageSize) ).getContent();
    }

    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Optional<Customer> findByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }
}
