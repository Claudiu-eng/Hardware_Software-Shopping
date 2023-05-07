package com.example.hardware_softwareshopping.repository;

import com.example.hardware_softwareshopping.model.Category;
import com.example.hardware_softwareshopping.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

    Optional<Product> findById(Long id);

    List<Product> findAll();

    Product save(Product product);

    void deleteById(Long id);

    List<Product> findByNameContainingOrDescriptionContaining(String nameKeyword, String descriptionKeyword);

    List<Product> findByPriceBetweenAndCategoryIn(double minPrice, double maxPrice, List<Category> categories);

    List<Product> findAllByCategoryIn(List<Category> categories);

    List<Product> findAllByPriceBetween(double minPrice,double maxPrice);
}
