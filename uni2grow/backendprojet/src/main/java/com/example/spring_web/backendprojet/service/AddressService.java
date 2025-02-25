package com.example.spring_web.backendprojet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.spring_web.backendprojet.entity.Address;
import com.example.spring_web.backendprojet.repository.AddressRepository;

@Service
public class AddressService implements GlobalServiceCrud<Address>  {
    
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void delete(Address address) {
        // TODO Auto-generated method stub
        addressRepository.delete(address);
    }

    @Override
    public void deleteAll(List<Address> addresses) {
        // TODO Auto-generated method stub
        addressRepository.deleteAll(addresses);
    }

    @Override
    public List<Address> saveAll(List<Address> addresses) {
        // TODO Auto-generated method stub
        return  addressRepository.saveAll( addresses );
    }

    @Override
    public Optional<Address> findById(Long identifier) {
        // TODO Auto-generated method stub
        return addressRepository.findById(identifier);
    }

    public void emptyTableAddress() {
        addressRepository.deleteAll();
    }

    @Override
    public List<Address> getAllElement() {
        // TODO Auto-generated method stub
        List<Address> addresses =  addressRepository.findAll();
        return addresses;
    }

    public Optional<Address> findByZipCode( String zipCode ) {
        return addressRepository.findByZipCode(zipCode);
    }
  
    public List<Address> getAddressesWithPagination( int pageNumber, int pageSize ) {
        // TODO Auto-generated method stub
        return (List<Address>) addressRepository.findAll( PageRequest.of(pageNumber, pageSize) ).getContent();
    }
}
