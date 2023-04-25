package com.example.hardware_softwareshopping.service.implementations;

import com.example.hardware_softwareshopping.model.Category;
import com.example.hardware_softwareshopping.model.Product;
import com.example.hardware_softwareshopping.model.Review;
import com.example.hardware_softwareshopping.repository.ProductRepository;
import com.example.hardware_softwareshopping.service.CategoryService;
import com.example.hardware_softwareshopping.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplementation implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductServiceImplementation(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {

        Optional<Category> category = categoryService.findById(product.getCategory().getId());
        product.setCategory(category.get());
        if(category.get()==null)
            return null;
        product.setReviewList(new ArrayList<>());
        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(Long id) {
        Optional<Product> product=productRepository.findById(id);
        productRepository.deleteById(id);
        return product.get();
    }

    @Override
    public List<Review> getReviews(Long productId) {
        Optional<Product> product=productRepository.findById(productId);
        if(product.get()==null)
            return null;
        return product.get().getReviewList();
    }
}
