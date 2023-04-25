package com.example.hardware_softwareshopping;

import com.example.hardware_softwareshopping.repository.UserRepository;
import com.example.hardware_softwareshopping.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
@SpringBootTest
class HardwareSoftwareShoppingApplicationTests {

    private UserService userService;

    @Mock
    private UserRepository userRepository;


    @BeforeEach
    void setUp(){
        initMocks(this);

    }






}
