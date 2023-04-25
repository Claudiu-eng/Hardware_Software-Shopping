package com.example.hardware_softwareshopping.service;

import com.example.hardware_softwareshopping.model.Customer;
import com.example.hardware_softwareshopping.model.Orders;
import org.springframework.stereotype.Component;

@Component
public interface OrdersService {

    Customer insertOrder(String email);

}
