package com.Etiqa.TechAssessment.controller;

import com.Etiqa.TechAssessment.entity.Customer;
import com.Etiqa.TechAssessment.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService service;

    @PostMapping("/saveCustomer")
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        log.info("Creating customer: {}", customer);
        Customer saved = service.createCustomer(customer);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Customer>> createCustomers(@RequestBody List<Customer> customers) {
        log.info("Bulk create customers: {}", customers);
        List<Customer> savedCustomers = service.createCustomers(customers);
        return ResponseEntity.ok(savedCustomers);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Customer customer = service.getCustomer(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> customers = service.getAll();
        return ResponseEntity.ok(customers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer dto) {
        Customer updated = service.updateCustomer(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/bulk")
    public ResponseEntity<List<Customer>> updateCustomers(@RequestBody List<Customer> customers){
        return ResponseEntity.ok(service.updateCustomers(customers));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/bulk")
    public ResponseEntity<Void> deleteCustomers(@RequestBody  List<Long> ids){
        service.deleteCustomers(ids);
        return ResponseEntity.noContent().build();
    }
}
