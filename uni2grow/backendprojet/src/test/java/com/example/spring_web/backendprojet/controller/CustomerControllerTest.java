package com.example.spring_web.backendprojet.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.spring_web.backendprojet.dto.bodyrequest.CreateAndUpdateCustomerDto;
import com.example.spring_web.backendprojet.dto.bodyrequest.CustomerFilter;
import com.example.spring_web.backendprojet.traitement.CustomerTraitement;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerTraitement customerTraitement;

/*     @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    } */

    @Test
    public void testGetAllCustomers() throws Exception {
        when(customerTraitement.getAllCustomers()).thenReturn(ResponseEntity.ok().build());
        mockMvc.perform(get("/customer"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateOneCustomer() throws Exception {
        CreateAndUpdateCustomerDto dto = new CreateAndUpdateCustomerDto();
        
        when(customerTraitement.createCustomer(dto)).thenReturn(ResponseEntity.ok().build());
        mockMvc.perform(post("/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCustomersPaginated() throws Exception {
        when(customerTraitement.getCustomersWithPagination(0, 10)).thenReturn(ResponseEntity.ok().build());
        mockMvc.perform(get("/customer/pagination")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllCustomersByFiltering() throws Exception {
        CustomerFilter filter = new CustomerFilter();
        when(customerTraitement.getCustomerFiltering(filter)).thenReturn(ResponseEntity.ok().build());
        mockMvc.perform(post("/customer/filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(filter)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateOneCustomer() throws Exception {
        CreateAndUpdateCustomerDto dto = new CreateAndUpdateCustomerDto();
        when(customerTraitement.updateOneCustomer(dto)).thenReturn(ResponseEntity.ok().build());
        mockMvc.perform(put("/customer/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteOneCustomer() throws Exception {
        when(customerTraitement.deleteOneCustomer(1L)).thenReturn(ResponseEntity.ok().build());
        mockMvc.perform(delete("/customer/delete/1"))
                .andExpect(status().isOk());
    }
}
