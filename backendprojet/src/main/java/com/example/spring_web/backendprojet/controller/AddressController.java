package com.example.spring_web.backendprojet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_web.backendprojet.dto.bodyrequest.AddressFilter;
import com.example.spring_web.backendprojet.dto.bodyrequest.CreateAndUpdateAddressDto;
import com.example.spring_web.backendprojet.traitement.AddressTraitement;

@RequestMapping("address")
@RestController
public class AddressController {
    
    @Autowired
    private AddressTraitement addressTraitement;

    @GetMapping
    public ResponseEntity<?> getAllAddress() {
        return addressTraitement.getAllAddress();
    }

    @PostMapping("/add")
    public ResponseEntity<?> createOneAddress( @RequestBody CreateAndUpdateAddressDto createAndUpdateAddressDto) {
        return addressTraitement.createAddress(createAndUpdateAddressDto);
    }

    @GetMapping( value = "/pagination", params = { "page", "size" } )
    public ResponseEntity<?> getAddressesPaginated( @RequestParam("page") int page, @RequestParam("size") int size  ) {
        return addressTraitement.getAddressesWithPagination(page, size);
    }

    @PostMapping("/filter")
    public ResponseEntity<?> getAllAddressByFiltering( @RequestBody AddressFilter addressFilter) {
        return ResponseEntity.ok("Address");
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateOneAddress(@RequestBody CreateAndUpdateAddressDto createAndUpdateAddressDto) {
        return addressTraitement.updateOneAddress(createAndUpdateAddressDto);
    }

    @DeleteMapping("/delete/{id}")  
    public ResponseEntity<?> deleteOneAddress( @PathVariable("idAddress") Long idAddress) {
        return addressTraitement.deleteOneAddress(idAddress);
    }

}
