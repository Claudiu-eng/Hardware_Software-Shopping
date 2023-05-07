package com.example.hardware_softwareshopping.service;

import com.example.hardware_softwareshopping.dto.PersonalReviewDTO;
import com.example.hardware_softwareshopping.dto.ShopCartDTO;
import com.example.hardware_softwareshopping.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CustomerService {

    Customer findByEmailAndPassword(String email, String password);

    Customer findByEmail(String email);
    List<Customer> findAll();
    Customer save(Customer employee);
    Optional<Customer> findById(Long id);

    ShopCartDTO getShopCart(String email);

    ShoppingCart deleteProduct(Customer customer, Product product);

    ShopCartDTO deleteProduct(String email,Product product);

    List<Orders> findAllByCustomer(String email);

    Customer insertReview(Review review, Long productID,String email);

    PersonalReviewDTO getPersonalReviews(String email);

    Customer deleteReview(String email,Long productID,Long reviewID);
}
