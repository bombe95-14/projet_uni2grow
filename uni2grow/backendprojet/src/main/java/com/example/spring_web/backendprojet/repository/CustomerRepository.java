package com.example.spring_web.backendprojet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_web.backendprojet.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    
    Optional<Customer> findByEmail( String email );
    Optional<Customer> findByPhone( String phone );

}
