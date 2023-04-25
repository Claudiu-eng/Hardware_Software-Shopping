package com.example.hardware_softwareshopping.controller;


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

    public AdminController(UserService userService, CategoryService categoryService, EmployeeService employeeService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.employeeService = employeeService;
    }

    @DeleteMapping("/delete_user/{email}")
    public ResponseEntity deleteUser(@PathVariable String email){

        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteByEmail(email));
    }

    @PostMapping("/insert_employee")
    public ResponseEntity insertEmployee(@RequestBody Employee employee){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.save(employee));
    }

    @PostMapping("/insert_category")
    public ResponseEntity insertCategory(@RequestBody Category category){
        System.out.println("Da");
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.save(category));
    }

    @GetMapping("/see_users")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }


//
//    private final AddressService addressService;
//    private final UserService userService;
//    private final EmployeeService employeeService;
//    private final CustomerService customerService;
//    private final CategoryService categoryService;
//
//    public AdminController(UserService userService, AddressService addressService, EmployeeService employeeService, CustomerService customerService, CategoryService categoryService) {
//        this.userService = userService;
//        this.addressService = addressService;
//        this.employeeService = employeeService;
//        this.customerService = customerService;
//        this.categoryService = categoryService;
//    }
//
//    @GetMapping("/log_out")
//    public String adminLogOut(){
//        return "logInPage";
//    }
//
//    @GetMapping("/admin_see_users")
//    public String manageUsers(Model model){
//
//
//        List<User> users=userService.findAllByRole(UserRole.EMPLOYEE);
//        List<User> users1=userService.findAllByRole(UserRole.CUSTOMER);
//        users.addAll(users1);
//        model.addAttribute("users",users);
//        model.addAttribute("empty",false);
//        model.addAttribute("myVar",false);
//
//        return "admin_page";
//    }
//
//    @GetMapping("/admin_add_category")
//    public String addCateg(){
//        return "admin_add_category";
//    }
//
//    @PostMapping("/admin_add_category/insert_categ")
//    public String insertCateg(Category category){
//
//        categoryService.save(category);
//
//        return "admin_page";
//    }
//
//    @GetMapping("/admin_add_user")
//    public String addUsers(Model model){
//        return "register";
//    }
//    @PostMapping("/admin_add_user/register")
//    public String registerNewUser(Employee employee, @RequestParam String city, @RequestParam String street , @RequestParam String number){
//
//        Address address = addressService.findByCityAndNumberAndStreet(city,Long.parseLong(number),street);
//        if(address!=null){
//            employee.setAddress(address);
//        }else{
//            Address address1 = new Address();
//            address1.setCity(city);
//            address1.setNumber(Long.parseLong(number));
//            address1.setStreet(street);
//            employee.setAddress(address1);
//            addressService.save(address1);
//           // employee.setRole(UserRole.EMPLOYEE);
//        }
//        userService.save(employee);
//
//        return "admin_page";
//
//    }
//
//    @PostMapping("/delete_user")
//    public String deleteUser(@RequestParam("userId") String  userId,Model model) {
//        // code to delete user with specified ID from database
//
//        System.out.println(userId);
//
//        Long id = Long.valueOf(userId);
//        userService.deleteById(id);
//
//        List<User> users=userService.findAllByRole(UserRole.EMPLOYEE);
//        List<User> users1=userService.findAllByRole(UserRole.CUSTOMER);
//        users.addAll(users1);
//        model.addAttribute("users",users);
//        model.addAttribute("empty",false);
//        model.addAttribute("myVar",false);
//
//        return "redirect:/admin_page/admin_see_users";
//    }
//


}
