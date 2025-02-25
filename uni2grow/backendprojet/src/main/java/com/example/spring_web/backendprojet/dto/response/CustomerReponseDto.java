package com.example.spring_web.backendprojet.dto.response;

import com.example.spring_web.backendprojet.dto.bodyrequest.CreateAndUpdateAddressDto;
import com.example.spring_web.backendprojet.entity.Customer;

public class CustomerReponseDto extends CreateAndUpdateAddressDto {
    
    private Long id;
    private String name;
    private String email;
    private String phone;

    public Long getId() {
        return id;
    }
    
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerReponseDto( Customer customer ){
        this.setId( customer.getId() );
        this.setCity( customer.getAddress().getCity() );
        this.setCountry( customer.getAddress().getCountry() );
        this.setState( customer.getAddress().getState() );
        this.setStreet( customer.getAddress().getStreet() );
        this.setZipCode( customer.getAddress().getZipCode() );
        this.setEmail( customer.getEmail() );
        this.setName( customer.getName() );
        this.setPhone( customer.getPhone() );
    }

}
