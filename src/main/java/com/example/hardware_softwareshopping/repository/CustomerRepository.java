package com.example.hardware_softwareshopping.repository;

import com.example.hardware_softwareshopping.model.Customer;
import com.example.hardware_softwareshopping.model.ShoppingCart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {

    Customer findByEmailAndPassword(String email,String password);

    List<Customer> findAll();



    Customer findByEmail(String email);
    Optional<Customer> findById(Long id);

    Customer save(Customer customer);


}
