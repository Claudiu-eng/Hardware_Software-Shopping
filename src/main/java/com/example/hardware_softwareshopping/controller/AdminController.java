package com.example.hardware_softwareshopping.controller;


import com.example.hardware_softwareshopping.dto.EmployeeDTO;
import com.example.hardware_softwareshopping.exceptions.ApiExceptionResponse;
import com.example.hardware_softwareshopping.model.Category;
import com.example.hardware_softwareshopping.model.Employee;
import com.example.hardware_softwareshopping.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/admin_page")
public class AdminController {

    private final UserService userService;
    private final CategoryService categoryService;
    private final EmployeeService employeeService;
    private final RaportService raportService;

    public AdminController(UserService userService, CategoryService categoryService, EmployeeService employeeService, RaportService raportService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.employeeService = employeeService;
        this.raportService = raportService;
    }

    @DeleteMapping("/delete_user/{email}")
    public ResponseEntity deleteUser(@PathVariable String email){

        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteByEmail(email));
    }

    @GetMapping("/total_users_connected")
    public ResponseEntity totalConnectedUsers(){

        return ResponseEntity.status(HttpStatus.OK).body(userService.totalNumberOfConnectedUsers());
    }

    @PostMapping("/insert_employee")
    public ResponseEntity insertEmployee(@RequestBody EmployeeDTO employee) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.save(employee));
    }

    @PostMapping("/insert_category")
    public ResponseEntity insertCategory(@RequestBody Category category){

        return ResponseEntity.status(HttpStatus.OK).body(categoryService.save(category));
    }

    @GetMapping("/see_users")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/see_raports")
    public ResponseEntity getRaports(){
        return ResponseEntity.status(HttpStatus.OK).body(raportService.exportData());
    }
}
