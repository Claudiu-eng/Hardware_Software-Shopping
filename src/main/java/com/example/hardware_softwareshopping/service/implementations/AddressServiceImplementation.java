package com.example.hardware_softwareshopping.service.implementations;

import com.example.hardware_softwareshopping.model.Address;
import com.example.hardware_softwareshopping.repository.AddressRepository;
import com.example.hardware_softwareshopping.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImplementation implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImplementation(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address findByCityAndNumberAndStreet(String city, Long number, String street) {
        return addressRepository.findByCityAndNumberAndStreet(city,number,street);
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }
}
