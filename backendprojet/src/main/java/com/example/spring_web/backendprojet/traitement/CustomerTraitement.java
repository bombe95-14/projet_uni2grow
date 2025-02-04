package com.example.spring_web.backendprojet.traitement;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.spring_web.backendprojet.dto.bodyrequest.CreateAndUpdateAddressDto;
import com.example.spring_web.backendprojet.dto.bodyrequest.CreateAndUpdateCustomerDto;
import com.example.spring_web.backendprojet.entity.Address;
import com.example.spring_web.backendprojet.entity.Customer;
import com.example.spring_web.backendprojet.service.AddressService;
import com.example.spring_web.backendprojet.service.CustomerService;

@Component
public class CustomerTraitement {  
    
    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    public ResponseEntity<?> getAllCustomers(){
        return ResponseEntity.ok( customerService.getAllElement() );
    }

    public ResponseEntity<?> getCustomersWithPagination( int pageNumber, int size ){
        return ResponseEntity.ok( customerService.getCustomersWithPagination( pageNumber, size ) );
    }

    public ResponseEntity<?> createCustomer( CreateAndUpdateCustomerDto createAndUpdateCustomerDto ){

        if ( Objects.isNull(createAndUpdateCustomerDto.getPhone()) || createAndUpdateCustomerDto.getPhone().isEmpty() || createAndUpdateCustomerDto.getPhone().isBlank() || 
           Objects.isNull(createAndUpdateCustomerDto.getName()) || createAndUpdateCustomerDto.getName().isEmpty() || createAndUpdateCustomerDto.getName().isBlank() || 
           Objects.isNull(createAndUpdateCustomerDto.getCreateAndUpdateAddressDto()) || Objects.isNull(createAndUpdateCustomerDto.getCreateAndUpdateAddressDto().getZipCode() ) ||  
            createAndUpdateCustomerDto.getCreateAndUpdateAddressDto().getZipCode().isEmpty() || createAndUpdateCustomerDto.getCreateAndUpdateAddressDto().getZipCode().isBlank() ) {
                return ResponseEntity.badRequest().body("Empty obligatories field");
        }

        Optional<Address> addressOptional =  addressService.findByZipCode( createAndUpdateCustomerDto.getCreateAndUpdateAddressDto().getZipCode().trim() );
        Address address = null;
        if ( addressOptional.isPresent() ) {
            address = addressOptional.get();
        } else {

            CreateAndUpdateAddressDto createAndUpdateAddressDto = createAndUpdateCustomerDto.getCreateAndUpdateAddressDto();

            if ( Objects.isNull(createAndUpdateAddressDto.getCity() ) || createAndUpdateAddressDto.getCity().isEmpty() || createAndUpdateAddressDto.getCity().isBlank() || 
            Objects.isNull( createAndUpdateAddressDto.getCountry()) || createAndUpdateAddressDto.getCountry().isEmpty() || createAndUpdateAddressDto.getCountry().isBlank() || 
             Objects.isNull(createAndUpdateAddressDto.getStreet())  || createAndUpdateAddressDto.getStreet().isEmpty() || createAndUpdateAddressDto.getStreet().isBlank() || 
             Objects.isNull(createAndUpdateAddressDto.getState() ) || createAndUpdateAddressDto.getState().isEmpty() || createAndUpdateAddressDto.getState().isBlank() ){
                 return ResponseEntity.badRequest().body("Empty obligatories field");
            }
 
        Address addressSave = new Address(); 
        addressSave.setCity( createAndUpdateAddressDto.getCity().trim() );
        addressSave.setCountry( createAndUpdateAddressDto.getCountry().trim() );
        addressSave.setStreet( createAndUpdateAddressDto.getStreet().trim() );
        addressSave.setState( createAndUpdateAddressDto.getState().trim() );
        addressSave.setZipCode( createAndUpdateAddressDto.getZipCode().trim() );
 
         address = addressService.save( addressSave ); 
        }

        Customer customer = new Customer();
        customer.setAddress(address);
        customer.setEmail( createAndUpdateCustomerDto.getEmail() );
        customer.setName( createAndUpdateCustomerDto.getName() );
        customer.setPhone( createAndUpdateCustomerDto.getPhone() );

        Customer customerSave = customerService.save(customer);
        return ResponseEntity.ok( customerSave );
    }

    public ResponseEntity<?> updateOneCustomer( CreateAndUpdateCustomerDto createAndUpdateCustomerDto ){

        if ( Objects.isNull(createAndUpdateCustomerDto.getIdCustomer()) || Objects.isNull(createAndUpdateCustomerDto.getPhone()) || createAndUpdateCustomerDto.getPhone().isEmpty() || createAndUpdateCustomerDto.getPhone().isBlank() || 
           Objects.isNull(createAndUpdateCustomerDto.getName()) || createAndUpdateCustomerDto.getName().isEmpty() || createAndUpdateCustomerDto.getName().isBlank() || 
           Objects.isNull(createAndUpdateCustomerDto.getCreateAndUpdateAddressDto()) || Objects.isNull(createAndUpdateCustomerDto.getCreateAndUpdateAddressDto().getZipCode() ) ||  
            createAndUpdateCustomerDto.getCreateAndUpdateAddressDto().getZipCode().isEmpty() || createAndUpdateCustomerDto.getCreateAndUpdateAddressDto().getZipCode().isBlank() ) {
                return ResponseEntity.badRequest().body("Empty obligatories field");
        }

        Optional<Customer> customerOptional = customerService.findById(createAndUpdateCustomerDto.getIdCustomer());
        if ( customerOptional.isEmpty() ) {
            return ResponseEntity.badRequest().body("Empty obligatories field");
        }

        Optional<Address> addressOptional =  addressService.findByZipCode( createAndUpdateCustomerDto.getCreateAndUpdateAddressDto().getZipCode().trim() );
        Address address = null;
        if ( addressOptional.isPresent() ) {
            address = addressOptional.get();
        } else {

            CreateAndUpdateAddressDto createAndUpdateAddressDto = createAndUpdateCustomerDto.getCreateAndUpdateAddressDto();
            
            if ( Objects.isNull(createAndUpdateAddressDto.getCity() ) || createAndUpdateAddressDto.getCity().isEmpty() || createAndUpdateAddressDto.getCity().isBlank() || 
            Objects.isNull( createAndUpdateAddressDto.getCountry()) || createAndUpdateAddressDto.getCountry().isEmpty() || createAndUpdateAddressDto.getCountry().isBlank() || 
             Objects.isNull(createAndUpdateAddressDto.getStreet())  || createAndUpdateAddressDto.getStreet().isEmpty() || createAndUpdateAddressDto.getStreet().isBlank() || 
             Objects.isNull(createAndUpdateAddressDto.getState() ) || createAndUpdateAddressDto.getState().isEmpty() || createAndUpdateAddressDto.getState().isBlank() ){
                 return ResponseEntity.badRequest().body("Empty obligatories field");
            }
 
        Address addressSave = new Address(); 
        addressSave.setCity( createAndUpdateAddressDto.getCity().trim() );
        addressSave.setCountry( createAndUpdateAddressDto.getCountry().trim() );
        addressSave.setStreet( createAndUpdateAddressDto.getStreet().trim() );
        addressSave.setState( createAndUpdateAddressDto.getState().trim() );
        addressSave.setZipCode( createAndUpdateAddressDto.getZipCode().trim() );
 
         address = addressService.save( addressSave ); 
        }

        Customer customer = new Customer();
        customer.setAddress(address);
        customer.setEmail( createAndUpdateCustomerDto.getEmail() );
        customer.setName( createAndUpdateCustomerDto.getName() );
        customer.setPhone( createAndUpdateCustomerDto.getPhone() );

        Customer customerSave = customerService.save(customer);
        return ResponseEntity.ok( customerSave );
    }

}
