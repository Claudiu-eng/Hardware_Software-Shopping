package com.example.hardware_softwareshopping.repository;

import com.example.hardware_softwareshopping.model.Customer;
import com.example.hardware_softwareshopping.model.Employee;
import com.example.hardware_softwareshopping.model.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart,Long> {

    ShoppingCart save(ShoppingCart shoppingCart);


}
