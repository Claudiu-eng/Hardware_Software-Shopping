package com.example.hardware_softwareshopping.service.implementations;

import com.example.hardware_softwareshopping.constants.UserRole;
import com.example.hardware_softwareshopping.dto.PersonalReviewDTO;
import com.example.hardware_softwareshopping.model.*;
import com.example.hardware_softwareshopping.repository.*;
import com.example.hardware_softwareshopping.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.PDLOverrideSupported;
import java.util.*;

@Service
public class CustomerServiceImplementation implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrdersRepository ordersRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public CustomerServiceImplementation(CustomerRepository customerRepository, UserRepository userRepository, ShoppingCartRepository shoppingCartRepository, OrdersRepository ordersRepository, ProductRepository productRepository, ReviewRepository reviewRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;

        this.ordersRepository = ordersRepository;
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Customer findByEmailAndPassword(String email, String password) {

        return customerRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer save(Customer customer) {
        if (userRepository.findByEmail(customer.getEmail()) != null)
            return null;
        customer.setUserRole(UserRole.CUSTOMER);
        customer.setNotificationList(new ArrayList<>());

        customer.setShoppingCart(new ShoppingCart());

        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public ShoppingCart deleteProduct(Customer customer, Product product) {
        ShoppingCart shoppingCart = customer.getShoppingCart();

        Set<Product> productSet = shoppingCart.getProducts();
        boolean q = true;
        for (Product p : productSet) {
            if (p.getDescription().equals(product.getDescription()) &&
                    p.getName().equals(product.getName()))
                q = false;
            product = p;
            break;
        }

        if (!q)
            shoppingCart.getQuantities().put(product, shoppingCart.getQuantities().get(product) - 1);
        else {
            return null;
        }
        shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() - product.getPrice());
        if (shoppingCart.getQuantities().get(product) == 0) {
            shoppingCart.getQuantities().remove(product);
            shoppingCart.getProducts().remove(product);
        }

        return shoppingCartRepository.save(shoppingCart);

    }

    @Override
    public Customer insertOrder(String email) {
        return null;
    }

    @Override
    public List<Orders> findAllByCustomer(String email) {

        Customer customer = customerRepository.findByEmail(email);
        if(customer==null)
            return null;

        return customer.getOrderList();

    }

    @Override
    public Customer insertReview(Review review, Long productID,String email) {
        Customer customer=customerRepository.findByEmail(email);
        if(customer==null)
            return null;
        Optional<Product> product1=productRepository.findById(productID);
        Product product=product1.get();
        if(product==null)
            return null;


        Review r=reviewRepository.save(review);
        product.getReviewList().add(r);
        customer.getReviewList().add(r);
        productRepository.save(product);
        Customer c=customerRepository.save(customer);

        return c;

    }

    public boolean contains(List<Review> list,Review p){
        for(Review r:list){
            if(r.getId().intValue()==p.getId().intValue())
                return true;
        }
        return false;
    }

    @Override
    public PersonalReviewDTO getPersonalReviews(String email) {
        Customer customer=customerRepository.findByEmail(email);
        if(customer==null)
            return null;
        List<Product> products=productRepository.findAll();


        List<Product> list=new ArrayList<>();
        for(Review review:customer.getReviewList()){
            for(Product product:products) {
                if (contains(product.getReviewList(),review)) {
                    list.add(product);
                }
            }
        }

        return new PersonalReviewDTO(list,customer.getReviewList());

    }

    private void deleteReviewFromList(List<Review> list,Review review){
        list.removeIf(r -> r.getId().intValue() == review.getId().intValue());
    }

    @Override
    public Customer deleteReview(String email, Long productID, Long reviewID) {
        Customer customer=customerRepository.findByEmail(email);
        if(customer==null)
            return null;
        Optional<Product> product1 = productRepository.findById(productID);
        Product product = product1.get();
        if(product==null)
            return null;
        Optional<Review> review1 = reviewRepository.findById(reviewID);
        Review review=review1.get();

        if(review==null)
            return null;
        /*
        * de ce trebuie sa apelez functia?
        * metoda .remove nu gaseste acelasi review?
        * oare repoul reviewurilor
        * returneaza adrese de memorie diferte?
        * */

        deleteReviewFromList(product.getReviewList(),review);
        deleteReviewFromList(customer.getReviewList(),review);

        productRepository.save(product);
        Customer c=customerRepository.save(customer);
        reviewRepository.delete(review);
        return c;

    }

}
