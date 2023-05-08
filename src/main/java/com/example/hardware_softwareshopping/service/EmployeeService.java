package com.example.hardware_softwareshopping.service;

import com.example.hardware_softwareshopping.constants.UserRole;

import com.example.hardware_softwareshopping.dto.EmployeeDTO;
import com.example.hardware_softwareshopping.dto.PersonalReviewDTO;
import com.example.hardware_softwareshopping.exceptions.ApiExceptionResponse;
import com.example.hardware_softwareshopping.model.Employee;
import com.example.hardware_softwareshopping.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeService {

    Employee findByEmailAndPassword(String email, String password);

    List<Employee> findAll();
    Employee save(EmployeeDTO employee) throws ApiExceptionResponse;

    Product deleteReview(Long productID,Long reviewID);


}
