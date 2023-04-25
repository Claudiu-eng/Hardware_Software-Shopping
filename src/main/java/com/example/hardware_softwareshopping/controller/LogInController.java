package com.example.hardware_softwareshopping.controller;


import com.example.hardware_softwareshopping.constants.UserRole;
import com.example.hardware_softwareshopping.dto.AuthDTO;
import com.example.hardware_softwareshopping.model.Customer;
import com.example.hardware_softwareshopping.model.User;
import com.example.hardware_softwareshopping.service.CustomerService;
import com.example.hardware_softwareshopping.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


//    @PostMapping
//    public ResponseEntity login(@RequestBody AuthDTO auth){
//        System.out.println(auth.getEmail());
//        return ResponseEntity.status(HttpStatus.OK).body(userService.findByEmailAndPassword(auth));
//    }

    @PostMapping("/register_customer")
    public ResponseEntity registerCustomer(@RequestBody Customer customer){


        Customer c = customerService.save(customer);
        return ResponseEntity.status(HttpStatus.OK).body(c);
    }

    @PostMapping
    public ResponseEntity logIn(@RequestBody AuthDTO authDTO){

        return ResponseEntity.status(HttpStatus.OK).body(userService.findByEmailAndPassword(authDTO));
    }

//
//    private final AddressService addressService;
//    private final UserService userService;
//    private final ShoppingCartService shoppingCartService;
//
//    public LogInController(AddressService addressService, UserService userService, ShoppingCartService shoppingCartService) {
//        this.addressService = addressService;
//        this.userService = userService;
//        this.shoppingCartService = shoppingCartService;
//    }
//
//    @GetMapping
//    public String logInPage(){
//        return "logInPage";
//    }
//
//    @PostMapping(value = "/process_logIn")
//    public String getConnectedUser(@RequestParam String username, @RequestParam String password, Model model){
//
//        User user = userService.findByEmailAndPassword(username,password);
//        if(user!=null) {
//            if(user.getRole()==UserRole.ADMIN){
//                model.addAttribute("empty",true);
//                return "admin_page";
//            }else if(user.getRole() == UserRole.EMPLOYEE){
//                model.addAttribute("empty",true);
//                return "employee_page";
//            }else{
//                //client page
//                model.addAttribute("empty",true);
//
//                model.addAttribute("product",user);
//                return "client_page";
//            }
//        }
//        return "logInPage";
//    }
//
//    @PostMapping("/insert")
//    public String register(Model model,Customer customer, @RequestParam String city, @RequestParam String street , @RequestParam String number){
//
//
//        Address address = addressService.findByCityAndNumberAndStreet(city,Long.parseLong(number),street);
//        if(address!=null){
//            customer.setAddress(address);
//        }else{
//            Address address1 = new Address();
//            address1.setCity(city);
//            address1.setNumber(Long.parseLong(number));
//            address1.setStreet(street);
//            customer.setAddress(address1);
//            addressService.save(address1);
//            customer.setRole(UserRole.CUSTOMER);
//        }
//        System.out.println(customer.getAddress().getCity());
//        userService.save(customer);
//
//        ShoppingCart shoppingCart = new ShoppingCart();
//        shoppingCart.setTotalPrice(0);
//        shoppingCart.setCustomer(customer);
//        shoppingCartService.save(shoppingCart);
//
//        //am o problema ,cum adaug shopping cartu si clientu la cascadare?
//        //salvez de 2 ori ca sa rezolv baiu
//
//        customer.setShoppingCart(shoppingCart);
//        userService.save(customer);
//
//        model.addAttribute("product",customer);
//        return "client_page";
//
//
//    }
//
//    @GetMapping("/register")
//    public String createAccount(){
//        return "create_account";
//    }



}
