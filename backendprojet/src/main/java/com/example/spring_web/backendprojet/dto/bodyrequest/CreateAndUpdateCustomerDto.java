package com.example.spring_web.backendprojet.dto.bodyrequest;

public class CreateAndUpdateCustomerDto {
    
    private Long idCustomer;

    private String name;
    private String email;
    private String phone;

    private CreateAndUpdateAddressDto createAndUpdateAddressDto;

    public CreateAndUpdateAddressDto getCreateAndUpdateAddressDto() {
        return createAndUpdateAddressDto;
    }


    public Long getIdCustomer() {
        return idCustomer;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

}
