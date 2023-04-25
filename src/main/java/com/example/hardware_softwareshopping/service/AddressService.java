package com.example.hardware_softwareshopping.service;

import com.example.hardware_softwareshopping.model.Address;
import org.springframework.stereotype.Component;

@Component
public interface AddressService {

    Address findByCityAndNumberAndStreet(String city, Long number, String street);

    Address save(Address address);
}
