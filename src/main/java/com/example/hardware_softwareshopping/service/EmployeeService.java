package com.example.hardware_softwareshopping.service;

import com.example.hardware_softwareshopping.constants.UserRole;

import com.example.hardware_softwareshopping.dto.PersonalReviewDTO;
import com.example.hardware_softwareshopping.model.Employee;
import com.example.hardware_softwareshopping.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeService {

    Employee findByEmailAndPassword(String email, String password);

    List<Employee> findAll();
    Employee save(Employee employee);

    Product deleteReview(Long productID,Long reviewID);


}
