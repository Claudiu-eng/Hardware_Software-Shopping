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

    @GetMapping("/see_products")
    public ResponseEntity getProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @PostMapping("/insert_product")
    public ResponseEntity insertProduct(@RequestBody Product product){


        return ResponseEntity.status(HttpStatus.OK).body(productService.save(product));

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

    @DeleteMapping("/delete_product/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){

        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(Long.valueOf(id)));

    }

//    private final ProductService productService;
//    private final CategoryService categoryService;
//
//    public EmployeeController(ProductService productService, CategoryService categoryService) {
//        this.productService = productService;
//        this.categoryService = categoryService;
//    }
//
//    @GetMapping("/employee_see_products")
//    public String manageProducts(Model model){
//
//        model.addAttribute("empty",false);
//        model.addAttribute("myVar",false);
//
//        List<Product> myList = productService.findAll();
//        List<ObjectForProduct> list = new ArrayList<>();
//        for(Product p:myList){
//
//            ObjectForProduct t = new ObjectForProduct(p.getId(),p.getName(),p.getDescription(),0,p.getStock(),p.getCategory().getName(),p.getPrice());
//            list.add(t);
//
//            t.setReview(0);
//        }
//
//
//        model.addAttribute("users",list);
//        return "employee_page";
//    }
//
//    @GetMapping("/employee_add_products")
//    public String addProduct(Model model){
//
//        List<Category> cater = categoryService.findAll();
//        model.addAttribute("categories", cater);
//        return "add_product";
//
//    }
//
//    @PostMapping("/delete_product")
//    public String deleteUser(@RequestParam("productId") String  productId,Model model) {
//        // code to delete user with specified ID from database
//
//        Long id = Long.valueOf(productId);
//        productService.deleteById(id);
//
//        model.addAttribute("empty",false);
//        model.addAttribute("myVar",false);
//
//        List<Product> myList = productService.findAll();
//        List<ObjectForProduct> list = new ArrayList<>();
//        for(Product p:myList){
//            ObjectForProduct t = new ObjectForProduct(p.getId(),p.getName(),p.getDescription(),0,p.getStock(),p.getCategory().getName(),p.getPrice());
//            list.add(t);
//            t.setReview(0);
//        }
//        model.addAttribute("users",list);
//
//        return "employee_page";
//    }
//
//    @PostMapping("/edit_product")
//    public String editProduct(@RequestParam("productId") String  productId,Model model) {
//        // code to delete user with specified ID from database
//
//        Long id = Long.valueOf(productId);
//
//        Optional<Product> pr = productService.findById(id);
//        if(pr.isEmpty())
//            return "employee_page";
//        Product p = pr.get();
//
//
//        List<Category> cater = categoryService.findAll();
//        model.addAttribute("categories", cater);
//        model.addAttribute("product",p);
//
//        return "edit_product";
//    }
//
//    @GetMapping("/log_out")
//    public String employeeLogOut(){
//        return "logInPage";
//    }
//
//    @PostMapping ("/employee_edit_product/update")
//    public String updateProduct(Product product,@RequestParam("idCat") String selectedCategory){
//        Optional<Product> p = productService.findById(product.getId());
//
//        Optional<Category> category = categoryService.findById(Long.valueOf(selectedCategory));
//
//        Product t = p.get();
//        t.setCategory(category.get());
//        t.setDescription(product.getDescription());
//        t.setName(product.getName());
//        t.setStock(product.getStock());
//        productService.save(t);
//        return "employee_page";
//
//
//    }
//
//    @PostMapping("/employee_add_product/insert")
//    public String submitForm(Product product,@RequestParam("idCateg") String selectedCategory) {
//        // Do something with the selected category IDs
//
//        Optional<Category> category = categoryService.findById(Long.valueOf(selectedCategory));
//
//        if(category.isPresent()){
//            product.setCategory(category.get());
//        }else{
//            product.setCategory(null);
//        }
//        product.setReviewList(null);
//        productService.save(product);
//
//        return "employee_page";
//    }


}
