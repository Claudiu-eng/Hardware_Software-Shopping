package com.example.hardware_softwareshopping.service;

import com.example.hardware_softwareshopping.dto.ProductFilterDTO;
import com.example.hardware_softwareshopping.model.Product;
import com.example.hardware_softwareshopping.model.Review;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface
ProductService {

    Optional<Product> findById(Long id);

    List<Product> findAll();
    Product save(Product product);
    Product deleteProduct(Long id);
    List<Review> getReviews(Long productId);
    List<Product> findAllByNameOrDescription(String keyWord);
    List<Product> findByPriceBetweenAndCategoryIn(ProductFilterDTO productFilterDTO);

}
