package com.Etiqa.TechAssessment.service;


import com.Etiqa.TechAssessment.entity.Customer;
import com.Etiqa.TechAssessment.exception.ResourceNotFoundException;
import com.Etiqa.TechAssessment.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    public final CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        Customer saved = customerRepository.save(customer);
        log.info("Customer created with ID: {}", saved.getId());
        return saved;
    }

    public List<Customer> createCustomers(List<Customer> customers) {
        return customerRepository.saveAll(customers);
    }

    public Customer getCustomer(Long id){
        return customerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Customer Not Found " + id));
    }

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Long id, Customer cust){
        Customer customer = getCustomer(id);
        customer.setFirstName(cust.getFirstName());
        customer.setLastName(cust.getLastName());
        customer.setEmailOffice(cust.getEmailOffice());
        customer.setEmailPersonal(cust.getEmailPersonal());
        customer.setFamilyMembers(cust.getFamilyMembers());

        Customer updated = customerRepository.save(customer);
        log.info("Customer updated with ID: {}", id);
        return updated;
    }

    public List<Customer> updateCustomers(List<Customer> customers){
        for (Customer customer : customers){
            if(customer.getId() == null){
                throw new IllegalArgumentException("Customer ID is missing!");
            }

            if(!customerRepository.existsById(customer.getId())){
                throw new IllegalArgumentException("Customer with ID " + customer.getId() + " does not exist!");
            }
        }
        return customers.stream()
                .map(customer -> updateCustomer(customer.getId(), customer))
                .collect(Collectors.toList());
    }

    public void deleteCustomer(Long id){
        if(!customerRepository.existsById(id)){
            throw new EntityNotFoundException("Customer Not Found with ID: " + id);
        }
        customerRepository.deleteById(id);
    }

    public void deleteCustomers(List<Long> ids){
        customerRepository.deleteAllById(ids);
    }

}
