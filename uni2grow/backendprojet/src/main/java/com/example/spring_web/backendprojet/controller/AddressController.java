package com.example.spring_web.backendprojet.controller;

import org.apache.coyote.BadRequestException;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_web.backendprojet.dto.bodyrequest.AddressFilter;
import com.example.spring_web.backendprojet.dto.bodyrequest.CreateAndUpdateAddressDto;
import com.example.spring_web.backendprojet.entity.Address;
import com.example.spring_web.backendprojet.traitement.AddressTraitement;

@RequestMapping("address")
@RestController
public class AddressController {
    
    @Autowired
    private AddressTraitement addressTraitement;

    @GetMapping
    @CrossOrigin( origins = "http://localhost:4200", methods = RequestMethod.GET )
    public ResponseEntity<?> getAllAddress() {
        return ResponseEntity.ok(addressTraitement.getAllAddress());
    }

    @PostMapping("/add")
    @CrossOrigin( origins = "http://localhost:4200", methods = RequestMethod.POST )
    public ResponseEntity<?> createOneAddress( @RequestBody CreateAndUpdateAddressDto createAndUpdateAddressDto) throws Exception {
        return ResponseEntity.ok(addressTraitement.createAddress(createAndUpdateAddressDto) );
    }

    @GetMapping( value = "/pagination", params = { "page", "size" } )
    @CrossOrigin( origins = "http://localhost:4200", methods = RequestMethod.GET )
    public ResponseEntity<?> getAddressesPaginated( @RequestParam("page") int page, @RequestParam("size") int size  ) {
        return  ResponseEntity.ok( addressTraitement.getAddressesWithPagination(page, size) );
        //addressTraitement.getAddressesWithPagination(page, size);
    }

    @PostMapping("/filter")
    @CrossOrigin( origins = "http://localhost:4200", methods = RequestMethod.POST )
    public ResponseEntity<?> getAllAddressByFiltering( @RequestBody AddressFilter addressFilter) {
        return ResponseEntity.ok(addressTraitement.getAddressesFiltering(addressFilter));
    }

    @PutMapping("/update")
    @CrossOrigin( origins = "http://localhost:4200", methods = RequestMethod.PUT )
    public ResponseEntity<?> updateOneAddress(@RequestBody CreateAndUpdateAddressDto createAndUpdateAddressDto) {
        return addressTraitement.updateOneAddress(createAndUpdateAddressDto);
    }

    @DeleteMapping("/delete/{idAddress}")  
    @CrossOrigin( origins = "http://localhost:4200", methods = RequestMethod.DELETE )
    public ResponseEntity<?> deleteOneAddress( @PathVariable("idAddress") Long idAddress) {
        return addressTraitement.deleteOneAddress(idAddress);
    }

}
