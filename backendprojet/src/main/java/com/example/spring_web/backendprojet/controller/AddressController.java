package com.example.spring_web.backendprojet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("address")
@RestController
public class AddressController {
    
    @GetMapping
    public ResponseEntity<?> getAllAddress() {
        return ResponseEntity.ok("Address");
    }

    @GetMapping
    public ResponseEntity<?> getAllAddress() {
        return ResponseEntity.ok("Address");
    }

}
