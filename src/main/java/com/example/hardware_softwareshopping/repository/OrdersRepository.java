package com.example.hardware_softwareshopping.repository;

import com.example.hardware_softwareshopping.model.Orders;
import com.example.hardware_softwareshopping.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders,Long> {


}
