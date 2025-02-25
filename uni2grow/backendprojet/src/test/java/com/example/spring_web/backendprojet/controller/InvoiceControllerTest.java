package com.example.spring_web.backendprojet.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.spring_web.backendprojet.dto.bodyrequest.CreateAndUpdateInvoiceDto;
import com.example.spring_web.backendprojet.dto.bodyrequest.InvoiceFilter;
import com.example.spring_web.backendprojet.traitement.InvoiceTraitement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private InvoiceTraitement invoiceTraitement;

    @InjectMocks
    private InvoiceController invoiceController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(invoiceController).build();
    }

    @Test
    public void testGetAllAddress() throws Exception {
      //  when(invoiceTraitement.getAllInvoice()).thenReturn(ResponseEntity.<String>ok("All Invoices"));
        mockMvc.perform(get("/invoice"))
                .andExpect(status().isOk())
                .andExpect(content().string("All Invoices"));
    }

    @Test
    public void testPrintOneInvoice() throws Exception {
        Long idInvoice = 1L;
      //  when(invoiceTraitement.getAllInvoice()).thenReturn((ResponseEntity<?>) ResponseEntity.ok("Invoice"));
        mockMvc.perform(get("/invoice/print/{idInvoice}", idInvoice))
                .andExpect(status().isOk())
                .andExpect(content().string("Invoice"));
    }

    @Test
    public void testCreateOneInvoice() throws Exception {
        CreateAndUpdateInvoiceDto dto = new CreateAndUpdateInvoiceDto();
      //  when(invoiceTraitement.createOneInvoice(dto)).thenReturn(ResponseEntity.ok("Invoice Created"));
        mockMvc.perform(post("/invoice/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"field\":\"value\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Invoice Created"));
    }

    @Test
    public void testGetAllAddressByFiltering() throws Exception {
        InvoiceFilter filter = new InvoiceFilter();
        mockMvc.perform(post("/invoice/filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"field\":\"value\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Address"));
    }

    @Test
    public void testUpdateOneInvoice() throws Exception {
        CreateAndUpdateInvoiceDto dto = new CreateAndUpdateInvoiceDto();
      //  when(invoiceTraitement.updateOneInvoice(dto)).thenReturn(ResponseEntity.ok("Invoice Updated"));
        mockMvc.perform(put("/invoice/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"field\":\"value\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Invoice Updated"));
    }

    @Test
    public void testGetAddressesPaginated() throws Exception {
        int page = 0;
        int size = 10;
       // when(invoiceTraitement.getInvoicesWithPagination(page, size)).thenReturn(ResponseEntity.ok("Paginated Invoices"));
        mockMvc.perform(get("/invoice/pagination")
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size)))
                .andExpect(status().isOk())
                .andExpect(content().string("Paginated Invoices"));
    }

    @Test
    public void testDeleteOneInvoice() throws Exception {
        Long idInvoice = 1L;
      //  when(invoiceTraitement.deleteOneInvoice(idInvoice)).thenReturn(ResponseEntity.ok("Invoice Deleted"));
        mockMvc.perform(delete("/invoice/delete/{idInvoice}", idInvoice))
                .andExpect(status().isOk())
                .andExpect(content().string("Invoice Deleted"));
    }
}
