package com.example.spring_web.backendprojet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_web.backendprojet.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
    
}
