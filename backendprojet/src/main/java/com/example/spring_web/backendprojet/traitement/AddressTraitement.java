package com.example.spring_web.backendprojet.traitement;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.spring_web.backendprojet.dto.bodyrequest.AddressFilter;
import com.example.spring_web.backendprojet.dto.bodyrequest.CreateAndUpdateAddressDto;
import com.example.spring_web.backendprojet.entity.Address;
import com.example.spring_web.backendprojet.service.AddressService;

@Component
public class AddressTraitement {
    
    @Autowired
    private AddressService addressService;

    public ResponseEntity<?> getAllAddress(){
        return ResponseEntity.ok( addressService.getAllElement() );
    }

    public ResponseEntity<?> getAddressesWithPagination( int pageNumber, int pageSize ){
        return ResponseEntity.ok( addressService.getAddressesWithPagination(pageNumber, pageSize) );
    }

    public ResponseEntity<?> createAddress( CreateAndUpdateAddressDto createAndUpdateAddressDto ){

        if ( Objects.isNull(createAndUpdateAddressDto.getCity() ) || createAndUpdateAddressDto.getCity().isEmpty() || createAndUpdateAddressDto.getCity().isBlank() || 
           Objects.isNull( createAndUpdateAddressDto.getCountry()) || createAndUpdateAddressDto.getCountry().isEmpty() || createAndUpdateAddressDto.getCountry().isBlank() || 
            Objects.isNull(createAndUpdateAddressDto.getStreet())  || createAndUpdateAddressDto.getStreet().isEmpty() || createAndUpdateAddressDto.getStreet().isBlank() || 
            Objects.isNull(createAndUpdateAddressDto.getState() ) || createAndUpdateAddressDto.getState().isEmpty() || createAndUpdateAddressDto.getState().isBlank() || 
            Objects.isNull(createAndUpdateAddressDto.getZipCode() ) || createAndUpdateAddressDto.getZipCode().isEmpty() || createAndUpdateAddressDto.getZipCode().isBlank() ) {
            return ResponseEntity.badRequest().body("Empty obligatories field");
        }

        if ( addressService.findByZipCode( createAndUpdateAddressDto.getZipCode().trim() ).isPresent() ) {
            return ResponseEntity.badRequest().body("Il existe une autre adresse ayant ce meme zipcode");
        }

       Address addressSave = new Address(); 
       addressSave.setCity( createAndUpdateAddressDto.getCity().trim() );
       addressSave.setCountry( createAndUpdateAddressDto.getCountry().trim() );
       addressSave.setStreet( createAndUpdateAddressDto.getStreet().trim() );
       addressSave.setState( createAndUpdateAddressDto.getState().trim() );
       addressSave.setZipCode( createAndUpdateAddressDto.getZipCode().trim() );

       Address address = addressService.save( addressSave ); 
       return ResponseEntity
              .status(201)
              .body( address );

    }

    public ResponseEntity<?> updateOneAddress( CreateAndUpdateAddressDto createAndUpdateAddressDto ){

        if (  Objects.isNull(createAndUpdateAddressDto.getIdAdress()) || Objects.isNull(createAndUpdateAddressDto.getCity() ) || createAndUpdateAddressDto.getCity().isEmpty() || createAndUpdateAddressDto.getCity().isBlank() || 
           Objects.isNull( createAndUpdateAddressDto.getCountry()) || createAndUpdateAddressDto.getCountry().isEmpty() || createAndUpdateAddressDto.getCountry().isBlank() || 
            Objects.isNull(createAndUpdateAddressDto.getStreet())  || createAndUpdateAddressDto.getStreet().isEmpty() || createAndUpdateAddressDto.getStreet().isBlank() || 
            Objects.isNull(createAndUpdateAddressDto.getState() ) || createAndUpdateAddressDto.getState().isEmpty() || createAndUpdateAddressDto.getState().isBlank() || 
            Objects.isNull(createAndUpdateAddressDto.getZipCode() ) || createAndUpdateAddressDto.getZipCode().isEmpty() || createAndUpdateAddressDto.getZipCode().isBlank() ) {
            return ResponseEntity.badRequest().body("Empty obligatories fields");
        }

        Optional <Address> addressOptional = addressService.findById( createAndUpdateAddressDto.getIdAdress() );
        if ( addressOptional.isEmpty() ) {
            return ResponseEntity.badRequest().body("Address not found");
        }

        if ( !addressOptional.get().getZipCode().equalsIgnoreCase( createAndUpdateAddressDto.getZipCode().trim() ) && addressService.findByZipCode( createAndUpdateAddressDto.getZipCode().trim() ).isPresent() ) {
            return ResponseEntity.badRequest().body("Il existe une autre adresse ayant ce meme zipcode");
        }
        
        addressOptional.get().setCity( createAndUpdateAddressDto.getCity().trim() );
        addressOptional.get().setCountry( createAndUpdateAddressDto.getCountry().trim() );
        addressOptional.get().setState( createAndUpdateAddressDto.getState() );
        addressOptional.get().setStreet( createAndUpdateAddressDto.getStreet() );
        addressOptional.get().setZipCode( createAndUpdateAddressDto.getZipCode() );

       Address address = addressService.save( addressOptional.get() ); 
       return ResponseEntity.ok( address );
    }

    public ResponseEntity<?> deleteOneAddress( Long idAddress ){

        Optional <Address> addressOptional = addressService.findById( idAddress );
        if ( addressOptional.isEmpty() ) {
            return ResponseEntity.badRequest().body("Address not found");
        }

        addressService.delete(addressOptional.get());

        return ResponseEntity.ok( " this address has been deleted " );

    }

    public ResponseEntity<?> updateOneAddress( Object object ){

        if ( object instanceof AddressFilter ) {
            
        }
        
        AddressFilter addressFilter = ( AddressFilter )object;

        List<Address> addresses = (List<Address>) getAllAddress().getBody();
       return ResponseEntity.ok( addresses );
    }

    public ResponseEntity<?> getAddressesFiltering( AddressFilter addressFilter ){
        
        List<Address> addresses = addressService.getAllElement();
        if ( Objects.nonNull( addressFilter.getCity() ) && !addressFilter.getCity().isEmpty() && !addressFilter.getCity().isBlank() ) {
            addresses = addresses.stream().filter( address -> address.getCity().toUpperCase().contains( addressFilter.getCity().trim().toUpperCase() ) ).toList();
        }

        if ( Objects.nonNull( addressFilter.getCountry() ) && !addressFilter.getCountry().isEmpty() && !addressFilter.getCountry().isBlank() ) {
            addresses = addresses.stream().filter( address -> address.getCountry().toUpperCase().contains( addressFilter.getCountry().trim().toUpperCase() ) ).toList();
        }

        if ( Objects.nonNull( addressFilter.getZipCode() ) && !addressFilter.getZipCode().isEmpty() && !addressFilter.getZipCode().isBlank() ) {
            addresses = addresses.stream().filter( address -> address.getZipCode().toUpperCase().contains( addressFilter.getZipCode().trim().toUpperCase() ) ).toList();   
        }

       return ResponseEntity.ok( addresses );
    }
   
}
