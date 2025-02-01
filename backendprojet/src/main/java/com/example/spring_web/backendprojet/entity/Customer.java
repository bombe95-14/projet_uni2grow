
package com.example.spring_web.backendprojet.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class Customer {
    
    private String name;
    private String email;
    private String phone;

    @OneToOne
    private Address address;

    @OneToMany()
    private Set<Invoice> invoices = new HashSet<>();

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

}