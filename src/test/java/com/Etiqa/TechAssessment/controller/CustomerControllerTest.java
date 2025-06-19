package com.Etiqa.TechAssessment.controller;

import com.Etiqa.TechAssessment.entity.Customer;
import com.Etiqa.TechAssessment.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    private Customer customer;

    @BeforeEach
    void setup() {
        customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
    }

    @Test
    void testCreateCustomer() throws Exception {
        Mockito.when(customerService.createCustomer(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(post("/api/customers/saveCustomer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void testGetCustomerById() throws Exception {
        Mockito.when(customerService.getCustomer(1L)).thenReturn(customer);

        mockMvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    void testGetAllCustomers() throws Exception {
        List<Customer> customers = Arrays.asList(customer);
        Mockito.when(customerService.getAll()).thenReturn(customers);

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }
}
