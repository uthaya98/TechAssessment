package com.Etiqa.TechAssessment.repository;

import com.Etiqa.TechAssessment.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product, Long> {
}
