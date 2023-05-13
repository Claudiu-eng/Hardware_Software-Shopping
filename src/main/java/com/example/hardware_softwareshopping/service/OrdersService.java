package com.example.hardware_softwareshopping.service;

import com.example.hardware_softwareshopping.exceptions.ApiExceptionResponse;
import com.example.hardware_softwareshopping.model.Customer;
import com.example.hardware_softwareshopping.model.Orders;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Component;

@Component
public interface OrdersService {

    Customer insertOrder(String email);

    Orders rejectOrder(Long id,String email)throws ApiExceptionResponse;
    Orders validateOrder(Long id,String email)throws ApiExceptionResponse;
}
