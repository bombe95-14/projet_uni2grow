package com.example.spring_web.backendprojet.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.example.spring_web.backendprojet.dto.bodyrequest.AddressFilter;
import com.example.spring_web.backendprojet.dto.bodyrequest.CreateAndUpdateAddressDto;
import com.example.spring_web.backendprojet.entity.Address;
import com.example.spring_web.backendprojet.repository.AddressRepository;
import com.example.spring_web.backendprojet.traitement.AddressTraitement;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AddressController.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
        @MockBean
        private AddressTraitement addressTraitement;
        private static final Logger log = Logger.getLogger(AddressControllerTest.class);


    @Mock
    private AddressRepository addressRepository;
    
     //   @InjectMocks
       // private AddressController addressController;
    
         @BeforeEach
        public void setup() {
            MockitoAnnotations.openMocks(this);

            Address address = new Address();
            Address address1 = new Address();
    
            address.setCity("Douala");
            address.setCountry("Cameroun");
            address.setStreet("Makepe missoke");
            address.setZipCode("12345");
    
            address1.setCity("Yaounde");
            address1.setCountry("Cameroun");
            address1.setStreet("Fougerolle");
            address1.setZipCode("123456");
    
            addressRepository.save( address );
            addressRepository.save( address1 );

        //verify(adminService).deleteDatabaseContents();
        // .andExpect(MockMvcResultMatchers.content().string("Success: User details successfully saved!"));
        //                 .andExpect(MockMvcResultMatchers.content().string("Fail: Email already exists!"));    
        //          when(userService.registerUser(any())).thenReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail: Failed to process request now. Try again later"));


        } 

    @Test
    public void testGetAllAddress() throws Exception {
       // when(addressTraitement.getAllAddress()).thenReturn(ResponseEntity.ok().build());
        //  given(userRepository.findByAlias("john_doe")).willReturn(Optional.empty());
        //  assertThat(response.getStatus()).isEqualTo(HttpStatus.
/*             OK.value());
                assertThat(response.getContentAsString()).isEqualTo(
                        jsonResult.write(
                                new MultiplicationResultAttempt(attempt.
            getUser(),
                                        attempt.getMultiplication(),
                                        attempt.getResultAttempt(),
                                        correct)
                        ).getJson()); */

        mockMvc.perform(get("/address"))
                .andExpect(status().isOk())
                .andExpect(  jsonPath("$.*").exists() )
                .andExpect(  jsonPath("$.[0].id").value(1L) )

                ;
    }

    @Test
    public void testCreateOneAddress() throws Exception {
        CreateAndUpdateAddressDto dto = new CreateAndUpdateAddressDto();
        dto.setCity("Douala");
        dto.setCountry("Cameroun");
        dto.setStreet("Makepe missoke");
        dto.setZipCode("12345");

       /*  mockMvc.perform(
                post("/address/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto))
                .accept("application/json")
                )
                .andExpect(status().isOk())

                //.andExpect( jsonPath("$.city").exists()  )   
                ; */ 


                 MockHttpServletResponse response = mockMvc.perform(
                    post("/address/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(dto))
                    .accept("application/json")
                    )
                    .andReturn().getResponse(); 
                    log.info("response:  -------------------------------------- " + response.getContentAsString().isEmpty() );
                  //  response.getContentAsString();

                    assertEquals(response.getStatus(), HttpStatus.OK.value() );


    }   

    @Test
    public void testGetAddressesPaginated() throws Exception {
      //  when(addressTraitement.getAddressesWithPagination(0, 10)).thenReturn(ResponseEntity.ok().build());
        mockMvc.perform(get("/address/pagination")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllAddressByFiltering() throws Exception {
        AddressFilter filter = new AddressFilter();
        when(addressTraitement.getAddressesFiltering(filter)).thenReturn(ResponseEntity.ok().build());
        mockMvc.perform(post("/address/filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(filter)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateOneAddress() throws Exception {
        CreateAndUpdateAddressDto dto = new CreateAndUpdateAddressDto();
        when(addressTraitement.updateOneAddress(dto)).thenReturn(ResponseEntity.ok().build());
        mockMvc.perform(put("/address/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteOneAddress() throws Exception {
        when(addressTraitement.deleteOneAddress(1L)).thenReturn(ResponseEntity.ok().build());
        mockMvc.perform(delete("/address/delete/1"))
                .andExpect(status().isOk());
    }
}
