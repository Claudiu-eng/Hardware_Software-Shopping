package com.example.hardware_softwareshopping.service.implementations;

import com.example.hardware_softwareshopping.dto.*;
import com.example.hardware_softwareshopping.exceptions.ApiExceptionResponse;
import com.example.hardware_softwareshopping.model.Category;
import com.example.hardware_softwareshopping.model.Product;
import com.example.hardware_softwareshopping.model.Review;
import com.example.hardware_softwareshopping.repository.CategoryRepository;
import com.example.hardware_softwareshopping.repository.ProductRepository;
import com.example.hardware_softwareshopping.service.CategoryService;
import com.example.hardware_softwareshopping.service.ProductService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImplementation(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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
    public Product save(ProductDTO productDTO) throws ApiExceptionResponse {
        if(productDTO.getCategory()==null)
            throw ApiExceptionResponse.builder().status(HttpStatus.NOT_FOUND).message("categ null").errors(Collections.singletonList("error.addrs.not_found")).build();

        Validator validator  = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);
        Set<ConstraintViolation<CategoryDTO>> violations1 = validator.validate(productDTO.getCategory());
        if (!violations.isEmpty()) {
            String msg = "";
            for (ConstraintViolation<ProductDTO> violation : violations) {
                msg+=violation.getMessage();
            }
            throw ApiExceptionResponse.builder().status(HttpStatus.NOT_FOUND).message(msg).errors(Collections.singletonList("error.addrs.not_found")).build();
        }
        if (!violations1.isEmpty()) {
            String msg = "";
            for (ConstraintViolation<CategoryDTO> violation : violations1) {
                msg+=violation.getMessage();
            }
            throw ApiExceptionResponse.builder().status(HttpStatus.NOT_FOUND).message(msg).errors(Collections.singletonList("error.addrs.not_found")).build();
        }

        Optional<Category> category = categoryRepository.findById(Long.parseLong(productDTO.getCategory().getId()));
        if(category.get()==null)
            throw ApiExceptionResponse.builder().status(HttpStatus.NOT_FOUND).message("categ null").errors(Collections.singletonList("error.addrs.not_found")).build();

        Product product = Product.builder().price(Float.parseFloat(productDTO.getPrice()))
                        .name(productDTO.getName()).description(productDTO.getDescription())
                        .stock(Integer.parseInt(productDTO.getStock())).build();
        product.setCategory(category.get());
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

    @Override
    public List<Product> findAllByNameOrDescription(String keyWord) {

        return productRepository.findByNameContainingOrDescriptionContaining(keyWord,keyWord);
    }

    @Override
    public List<Product> findByPriceBetweenAndCategoryIn(ProductFilterDTO productFilterDTO) {

        List<Product> products = new ArrayList<>();

        List<Category> categories = productFilterDTO.getCategories().stream()
                .map(categoryRepository::findById).filter(Optional::isPresent)
                .map(Optional::get).collect(Collectors.toList());

       if(productFilterDTO.getPrices().size()==0){
           products = productRepository.findAllByCategoryIn(categories);
       }else {
           for (String prices : productFilterDTO.getPrices()) {
               double minPrice, maxPrice;
               if (prices.contains("+")) {
                   prices = prices.substring(0, prices.length() - 1);
                   minPrice = Double.parseDouble(prices);
                   maxPrice = 999999999.0;
               } else {
                   String[] price = prices.split("-");
                   minPrice = Double.parseDouble(price[0]);
                   maxPrice = Double.parseDouble(price[1]);
               }
               List<Product> productsInRange;
               if (categories.size() > 0)
                   productsInRange = productRepository.findByPriceBetweenAndCategoryIn(minPrice, maxPrice, categories);
               else productsInRange = productRepository.findAllByPriceBetween(minPrice, maxPrice);

               products.addAll(productsInRange);
           }
       }

        return products;
    }
}
