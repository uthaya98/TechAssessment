package com.Etiqa.TechAssessment.repository;

import com.Etiqa.TechAssessment.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
