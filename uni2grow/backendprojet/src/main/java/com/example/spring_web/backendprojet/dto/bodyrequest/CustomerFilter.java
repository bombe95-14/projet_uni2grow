package com.example.spring_web.backendprojet.dto.bodyrequest;

public class CustomerFilter {
    
    private String name;
    private String email;
    private String phone;
    private String country;
    private String street;
    private String state;

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }


    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }


    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
