package com.Etiqa.TechAssessment.service;

import com.Etiqa.TechAssessment.entity.Customer;
import com.Etiqa.TechAssessment.exception.ResourceNotFoundException;
import com.Etiqa.TechAssessment.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
    }

    @Test
    void testCreateCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        Customer result = customerService.createCustomer(customer);
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }

    @Test
    void testGetCustomerById() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Customer result = customerService.getCustomer(1L);
        assertEquals("Doe", result.getLastName());
    }

    @Test
    void testGetCustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> customerService.getCustomer(1L));
    }

    @Test
    void testGetAllCustomers() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));
        List<Customer> result = customerService.getAll();
        assertEquals(1, result.size());
    }

    @Test
    void testDeleteCustomer() {
        when(customerRepository.existsById(1L)).thenReturn(true);
        customerService.deleteCustomer(1L);
        verify(customerRepository, times(1)).deleteById(1L);
    }
}
