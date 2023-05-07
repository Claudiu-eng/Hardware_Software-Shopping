package com.example.hardware_softwareshopping.controller;


import com.example.hardware_softwareshopping.model.Product;
import com.example.hardware_softwareshopping.service.CategoryService;
import com.example.hardware_softwareshopping.service.EmployeeService;
import com.example.hardware_softwareshopping.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/employee_page")
public class EmployeeController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final EmployeeService employeeService;

    public EmployeeController(ProductService productService, CategoryService categoryService, EmployeeService employeeService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.employeeService = employeeService;
    }

    @GetMapping("/categories")
    public ResponseEntity getCategories(){

        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
    }


    @PostMapping("/see_reviews/{productID}")
    public ResponseEntity seeReviews(@PathVariable Long productID){

        return ResponseEntity.status(HttpStatus.OK).body(productService.getReviews(productID));

    }

    @DeleteMapping("/delete_review/{productID}/{reviewID}")
    public ResponseEntity deleteReview(@PathVariable Long productID,@PathVariable Long reviewID){

        return ResponseEntity.status(HttpStatus.OK).body(employeeService.deleteReview(productID,reviewID)
        );

    }

}
