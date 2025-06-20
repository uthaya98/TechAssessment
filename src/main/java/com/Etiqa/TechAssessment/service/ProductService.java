package com.Etiqa.TechAssessment.service;

/*import com.Etiqa.TechAssessment.dto.ProductDto;*/
import com.Etiqa.TechAssessment.entity.Customer;
import com.Etiqa.TechAssessment.entity.Product;
import com.Etiqa.TechAssessment.exception.ResourceNotFoundException;
import com.Etiqa.TechAssessment.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    public final ProductRepository productRepository;

    public Product createProduct(Product product){
        Product saved = productRepository.save(product);
        log.info("Book have saved ID: {}", saved.getId());
        return saved;
    }

    public List<Product> createProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    public Product getProduct(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book Not found " + id));
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

   public Product updateProduct(Long id, Product prod){
        Product product = getProduct(id);
        product.setBookTitle(prod.getBookTitle());
        product.setBookPrice(prod.getBookPrice());
        product.setBookQuantity(prod.getBookQuantity());
        Product updated = productRepository.save(product);
        log.info("Product updated with ID: {}", id);
        return updated;
    }

    public List<Product> updateProducts(List<Product> products) {
        for (Product product : products) {
            if (product.getId() == null) {
                throw new IllegalArgumentException("Product ID is missing!");
            }
            if (!productRepository.existsById(product.getId())) {
                throw new IllegalArgumentException("Product with ID " + product.getId() + " does not exist!");
            }
        }
        return products.stream()
                .map(product -> updateProduct(product.getId(), product))
                .collect(Collectors.toList());
    }

    public void deleteProduct(Long id){
        if(!productRepository.existsById(id)){
            throw new EntityNotFoundException("Product Not Found with ID: " + id);
        }
        productRepository.deleteById(id);
    }

    public void deleteProducts(List<Long> ids){
        productRepository.deleteAllById(ids);
    }


}
