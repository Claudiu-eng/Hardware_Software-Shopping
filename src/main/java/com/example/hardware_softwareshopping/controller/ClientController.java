package com.example.hardware_softwareshopping.controller;


import com.example.hardware_softwareshopping.dto.ShopCartDTO;
import com.example.hardware_softwareshopping.model.*;
import com.example.hardware_softwareshopping.service.CustomerService;
import com.example.hardware_softwareshopping.service.OrdersService;
import com.example.hardware_softwareshopping.service.ProductService;
import com.example.hardware_softwareshopping.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/client_page")
public class ClientController {

    private final ProductService productService;
    private final ShoppingCartService shoppingCartService;
    private final CustomerService customerService;
    private final OrdersService ordersService;

    public ClientController(ProductService productService, ShoppingCartService shoppingCartService, CustomerService customerService, OrdersService ordersService) {
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
        this.customerService = customerService;
        this.ordersService = ordersService;
    }

    @PostMapping("/lasa_review/{productID}/{email}")
    public ResponseEntity insertReview(@PathVariable Long productID,@PathVariable String email, @RequestBody Review review){

        return ResponseEntity.status(HttpStatus.OK).body(customerService.insertReview(review,productID,email));
    }

    @PostMapping("/see_personal_review/{email}")
    public ResponseEntity getPersonalReviews(@PathVariable String email){

        return ResponseEntity.status(HttpStatus.OK).body(customerService.getPersonalReviews(email));
    }

    @PostMapping("/see_reviews/{productID}")
    public ResponseEntity getReviews(@PathVariable Long productID){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getReviews(productID));
    }

    @DeleteMapping("/delete_personal_review/{email}/{productID}/{reviewID}")
    public ResponseEntity deletePersonalReview(@PathVariable String email,@PathVariable Long productID,@PathVariable Long reviewID){

        return ResponseEntity.status(HttpStatus.OK).body(customerService.deleteReview(email,productID,reviewID));

    }


    @PostMapping("/delete_product/{email}")
    public ResponseEntity deleteProduct(@PathVariable String email,@RequestBody Product product){

        return ResponseEntity.status(HttpStatus.OK).body(customerService.deleteProduct(email,product));
    }

    @PostMapping("/see_shopping_cart_products/{email}")
    public ResponseEntity seeShopCart(@PathVariable String email){

        return ResponseEntity.status(HttpStatus.OK).body(customerService.getShopCart(email));
    }

    @PostMapping("/see_orders/{email}")
    public ResponseEntity seeOrders(@PathVariable String email){

        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAllByCustomer(email));
    }

    @PostMapping("/buy_product/{email}")
    public ResponseEntity buyProduct(@PathVariable String email,@RequestBody Product product){

        return ResponseEntity.status(HttpStatus.OK).body(shoppingCartService.insertProduct(product,email));
    }

    @PostMapping("/place_order/{email}")
    public ResponseEntity placeOrder(@PathVariable String email){

        return ResponseEntity.status(HttpStatus.OK).body(
                ordersService.insertOrder(email)
        );
    }



}
