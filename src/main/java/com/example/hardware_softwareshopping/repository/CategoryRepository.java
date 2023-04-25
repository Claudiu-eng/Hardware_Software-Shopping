package com.example.hardware_softwareshopping.repository;

import com.example.hardware_softwareshopping.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {

    Category save(Category category);

    List<Category> findAll();

    Optional<Category> findById(Long id);


}
