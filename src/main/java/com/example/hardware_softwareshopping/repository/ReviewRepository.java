package com.example.hardware_softwareshopping.repository;

import com.example.hardware_softwareshopping.model.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review,Long> {

    Review save(Review review);

}
