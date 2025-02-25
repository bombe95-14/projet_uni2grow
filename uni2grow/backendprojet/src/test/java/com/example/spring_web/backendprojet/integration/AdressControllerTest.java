package com.example.spring_web.backendprojet.integration;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.example.spring_web.backendprojet.dto.bodyrequest.CreateAndUpdateAddressDto;
import com.example.spring_web.backendprojet.entity.Address;
import com.example.spring_web.backendprojet.repository.AddressRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@Import(PostgresContainerTest.class)
@AutoConfigureMockMvc
public class AdressControllerTest {
    

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AddressRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        insertUsers();
    }

    @Test
    public void testGetAllAddress() throws Exception {
        mockMvc.perform(get("/address"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].street", is("un")));
    ;
    }


     @Test
    public void testCreateOneAddress() throws Exception {
        CreateAndUpdateAddressDto dto = new CreateAndUpdateAddressDto();
        dto.setCity("Douala");
        dto.setCountry("Cameroun");
        dto.setStreet("Makepe missoke");
        dto.setZipCode("12345");
        dto.setState("Litoral");

                 MockHttpServletResponse response = mockMvc.perform(
                    post("/address/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(dto))
                    .accept("application/json")
                    )
                    .andReturn().getResponse(); 
                  //  response.getContentAsString();

                    assertEquals(response.getStatus(), HttpStatus.OK.value() );


    }   

 
    private void insertUsers() {
        userRepository.save(new Address("un", "deux@example.com", "trois", "quatre", "cinq"));
        userRepository.save(new Address("un un", "email2@deux un.com", "trois trois", "quatre quatre", "cinq cinq"));
   
        userRepository.flush();
    }
    
}
