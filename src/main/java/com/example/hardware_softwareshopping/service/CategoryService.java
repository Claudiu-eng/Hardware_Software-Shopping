package com.example.hardware_softwareshopping.service;

import com.example.hardware_softwareshopping.model.Category;
import com.example.hardware_softwareshopping.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public interface CategoryService {

    Category save(Category category);

    List<Category> findAll();

    Optional<Category> findById(Long id);
}
