package com.example.spring_web.backendprojet.unit_test.test_address;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.spring_web.backendprojet.controller.AddressController;
import com.example.spring_web.backendprojet.entity.Address;
import com.example.spring_web.backendprojet.service.AddressService;

@WebMvcTest(controllers = AddressController.class)
public class AddressControllerTest {
    
    private MockMvc mockMvc;

    @Autowired
    private AddressService addressService;

   @BeforeAll
    public void methodeAppeleeAvantChaqueTest() {
        addressService.saveAll(
            List.of(
                new Address( "Makepe missoke", "Douala", "Littoral", "Ville 1", "Cameroun"),
                new Address( "Gendarmerie Medong", "Yaoundé", "Centre", "Ville 1", "Cameroun"),

                new Address("Charles de Gaulle", "Paris", "Province" , "Ville 2", "France")
            //    new Address("Rue 3", "Ville 3", "Pays 3")
            )
        );
    }

    @AfterAll
    public void methodeAppeleeApresChaqueTest() {
        addressService.emptyTableAddress();
    } 

   

    @BeforeAll
    public static void methodeAppeleeAvantTousLesTests() {
        
        

    }

    @AfterAll
    public static void methodeAppeleeApresTousLesTests() {
        
    }

}
