package com.example.hardware_softwareshopping.repository;

import com.example.hardware_softwareshopping.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address,Long> {

    Address findByCityAndNumberAndStreet(String city,Long number,String street);

}
