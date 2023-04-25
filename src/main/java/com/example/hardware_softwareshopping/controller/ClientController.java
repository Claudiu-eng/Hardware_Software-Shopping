package com.example.hardware_softwareshopping.controller;


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

        Customer customer = customerService.findByEmail(email);
        if(customer==null)
            return null;
        Map<Product,Integer> map=customerService.deleteProduct(customer,product).getQuantities();
        ShopCart obj= new ShopCart();
        obj.setProducts(new ArrayList<>());
        obj.setQuantities(new ArrayList<>());

        for(Map.Entry<Product,Integer> p:map.entrySet()){
            obj.getProducts().add(p.getKey());
            obj.getQuantities().add(p.getValue());
        }

        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @PostMapping("/see_shopping_cart_products/{email}")
    public ResponseEntity seeShopCart(@PathVariable String email){

        Map<Product,Integer> map = customerService.findByEmail(email).getShoppingCart().getQuantities();

        ShopCart obj= new ShopCart();
        obj.setProducts(new ArrayList<>());
        obj.setQuantities(new ArrayList<>());

        for(Map.Entry<Product,Integer> p:map.entrySet()){
            obj.getProducts().add(p.getKey());
            obj.getQuantities().add(p.getValue());
        }

        return ResponseEntity.status(HttpStatus.OK).body(obj);
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

    @GetMapping("/see_products")
    public ResponseEntity getProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }



//    private final ProductService productService;
//    private final CategoryService categoryService;
//    private final CustomerService customerService;
//    private final ShoppingCartService shoppingCartService;
//
//    public ClientController(ProductService productService, CategoryService categoryService, CustomerService customerService, ShoppingCartService shoppingCartService) {
//        this.productService = productService;
//        this.categoryService = categoryService;
//        this.customerService = customerService;
//        this.shoppingCartService = shoppingCartService;
//    }
//
//    @Transactional
//    @PostMapping("/delete_product")
//    public String deleteProduct(Model model,@RequestParam("id") String  clientId,@RequestParam("productId") String  productId){
//
//        //i have oroblem here
//        Optional<Product> p1 = productService.findById(Long.valueOf(productId));
//        Product product = p1.get();
//        Optional<Customer> customer1 = customerService.findById(Long.valueOf(clientId));
//
//        ShoppingCart shoppingCart = shoppingCartService.findByCustomer(customer1.get());
//
//        if(shoppingCart.getQuantities().get(product)<=1){
//            //delete from shocart that product
//            shoppingCart.getQuantities().remove(product);
//            shoppingCart.getProducts().remove(product);
//        }else{
//            int n = shoppingCart.getQuantities().get(product);
//            shoppingCart.getQuantities().put(product,n-1);
//        }
//        product.setStock(product.getStock()+1);
//        productService.save(product);
//        shoppingCart.setTotalPrice(shoppingCart.getTotalPrice()-product.getPrice());
//        shoppingCartService.save(shoppingCart);
//
//        Customer customer = customer1.get();
//        model.addAttribute("empty",false);
//        model.addAttribute("myVar",false);
//
//
//        List<ObjectForProduct> list = new ArrayList<>();
//        for(Map.Entry<Product,Integer> p: customer.getShoppingCart().getQuantities().entrySet()){
//            ObjectForProduct t1 = new ObjectForProduct(p.getKey().getId(),p.getKey().getName(),p.getKey().getDescription(),0,p.getKey().getStock()
//                    ,p.getKey().getCategory().getName(),p.getKey().getPrice());
//            t1.setCantitate(p.getValue());
//            list.add(t1);
//            t1.setReview(0);
//        }
//
//        model.addAttribute("shop",true);
//        model.addAttribute("users",list);
//        model.addAttribute("product",customer1.get());
//        return "client_page";
//
//    }
//
//    @GetMapping("/client_see_shopping_cart")
//    @Transactional
//    public String manageCart(@RequestParam("id") String  id,Model model){
//
//
//        model.addAttribute("empty",false);
//        model.addAttribute("myVar",false);
//        Optional<Customer> customer1 = customerService.findById(Long.valueOf(id));
//        Customer customer = customer1.get();
//
//        List<ObjectForProduct> list = new ArrayList<>();
//        for(Map.Entry<Product,Integer> p: customer.getShoppingCart().getQuantities().entrySet()){
//            ObjectForProduct t1 = new ObjectForProduct(p.getKey().getId(),p.getKey().getName(),p.getKey().getDescription(),0,p.getKey().getStock()
//                    ,p.getKey().getCategory().getName(),p.getKey().getPrice());
//            t1.setCantitate(p.getValue());
//            list.add(t1);
//            t1.setReview(0);
//        }
//
//        model.addAttribute("shop",true);
//        model.addAttribute("users",list);
//        model.addAttribute("product",customer);
//        return "client_page";
//    }
//
//    @Transactional
//    @PostMapping("/buy_product")
//    public String addProduct(Model model,@RequestParam("id") String  clientId,@RequestParam("productId") String  productId){
//
//        //i have oroblem here
//        Optional<Product> p1 = productService.findById(Long.valueOf(productId));
//        Product product = p1.get();
//        Optional<Customer> customer = customerService.findById(Long.valueOf(clientId));
//
//        ShoppingCart shoppingCart = shoppingCartService.findByCustomer(customer.get());
//
//
//        if(product.getStock()>=1){
//            product.setStock(product.getStock()-1);
//
//            if(shoppingCart.getProducts().contains(product)){
//
//                int n = shoppingCart.getQuantities().get(product);
//                shoppingCart.getQuantities().put(product,n+1);
//            }else {
//                shoppingCart.getProducts().add(product);
//                shoppingCart.addProduct(product,1);
//            }
//            productService.save(product);
//        }
//        shoppingCart.setTotalPrice(shoppingCart.getTotalPrice()+product.getPrice());
//        shoppingCartService.save(shoppingCart);
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
//
//        model.addAttribute("shop",false);
//        model.addAttribute("users",list);
//        model.addAttribute("product",customer.get());
//        return "client_page";
//
//    }
//
//    @GetMapping("/client_see_products")
//    public String manageProducts(@RequestParam("id") String  id,Model model){
//
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
//        Optional<Customer> customer = customerService.findById(Long.valueOf(id));
//        model.addAttribute("shop",false);
//        model.addAttribute("users",list);
//        model.addAttribute("product",customer.get());
//        return "client_page";
//    }
//
//
//    @GetMapping("/log_out")
//    public String employeeLogOut(){
//        return "logInPage";
//    }

}
