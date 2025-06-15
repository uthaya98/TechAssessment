package com.Etiqa.TechAssessment.controller;


/*import com.Etiqa.TechAssessment.dto.ProductDto;*/
import com.Etiqa.TechAssessment.entity.Customer;
import com.Etiqa.TechAssessment.entity.Product;
import com.Etiqa.TechAssessment.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    public final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product){
        log.info("Creating new Product: {}", product);
        Product saved = productService.createProduct(product);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Product>> createProducts(@RequestBody List<Product> products) {
        log.info("Bulk create products: {}", products);
        return ResponseEntity.ok(productService.createProducts(products));
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id){
        return productService.getProduct(id);
    }

    @GetMapping
    public List<Product> getAll(){
        return productService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product dto){
        Product updated = productService.updateProduct(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/bulk")
    public ResponseEntity<List<Product>> updateProducts(@RequestBody List<Product> products){
        return ResponseEntity.ok(productService.updateProducts(products));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/bulk")
    public ResponseEntity<Void> deleteProducts(@RequestBody List<Long> ids){
        productService.deleteProducts(ids);
        return ResponseEntity.noContent().build();
    }
}
