package com.example.hardware_softwareshopping.controller;


import com.example.hardware_softwareshopping.constants.UserRole;
import com.example.hardware_softwareshopping.dto.AuthDTO;
import com.example.hardware_softwareshopping.dto.CustomerDTO;
import com.example.hardware_softwareshopping.dto.UserResetPasswordDTO;
import com.example.hardware_softwareshopping.exceptions.ApiExceptionResponse;
import com.example.hardware_softwareshopping.model.Customer;
import com.example.hardware_softwareshopping.model.User;
import com.example.hardware_softwareshopping.service.CustomerService;
import com.example.hardware_softwareshopping.service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/logIn")
public class LogInController {

    private final UserService userService;
    private final CustomerService customerService;

    public LogInController(UserService userService, CustomerService customerService) {
        this.userService = userService;
        this.customerService = customerService;
    }

    @PostMapping("/register_customer")
    public ResponseEntity registerCustomer(@RequestBody CustomerDTO customer) throws ApiExceptionResponse{

        return ResponseEntity.status(HttpStatus.OK).body(customerService.save(customer));

    }

    @PostMapping
    public ResponseEntity logIn(@RequestBody AuthDTO authDTO){

        return ResponseEntity.status(HttpStatus.OK).body(userService.findByEmailAndPassword(authDTO));
    }

    @PostMapping("/logOut/{email}")
    public ResponseEntity logOut(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK).body(userService.logOut(email));
    }

    @PostMapping("/send_code/{email}")
    public ResponseEntity sendCode(@PathVariable String email)throws ApiExceptionResponse{
        return ResponseEntity.status(HttpStatus.OK).body(userService.sendCode(email));
    }

    @PutMapping("/reset_password")
    public ResponseEntity resetPassword(@RequestBody UserResetPasswordDTO userResetPasswordDTO) throws ApiExceptionResponse{

        return ResponseEntity.status(HttpStatus.OK).body(userService.resetPassword(userResetPasswordDTO));
    }

}
