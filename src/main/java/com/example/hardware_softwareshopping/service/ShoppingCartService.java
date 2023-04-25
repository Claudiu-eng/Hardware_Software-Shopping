package com.example.hardware_softwareshopping.service;

import com.example.hardware_softwareshopping.model.Customer;
import com.example.hardware_softwareshopping.model.Product;
import com.example.hardware_softwareshopping.model.ShoppingCart;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ShoppingCartService {


    ShoppingCart save(ShoppingCart shoppingCart);
    ShoppingCart insertProduct(Product product,String email);

}
