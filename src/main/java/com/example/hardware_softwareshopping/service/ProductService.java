package com.example.hardware_softwareshopping.service;

import com.example.hardware_softwareshopping.dto.ProductDTO;
import com.example.hardware_softwareshopping.dto.ProductFilterDTO;
import com.example.hardware_softwareshopping.exceptions.ApiExceptionResponse;
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
    Product save(ProductDTO product) throws ApiExceptionResponse;
    Product deleteProduct(Long id);
    List<Review> getReviews(Long productId);
    List<Product> findAllByNameOrDescription(String keyWord);
    List<Product> findByPriceBetweenAndCategoryIn(ProductFilterDTO productFilterDTO);

}
