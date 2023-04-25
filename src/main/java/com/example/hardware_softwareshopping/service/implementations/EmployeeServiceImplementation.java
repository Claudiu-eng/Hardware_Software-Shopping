package com.example.hardware_softwareshopping.service.implementations;

import com.example.hardware_softwareshopping.constants.UserRole;
import com.example.hardware_softwareshopping.model.Customer;
import com.example.hardware_softwareshopping.model.Employee;
import com.example.hardware_softwareshopping.model.Product;
import com.example.hardware_softwareshopping.model.Review;
import com.example.hardware_softwareshopping.repository.CustomerRepository;
import com.example.hardware_softwareshopping.repository.EmployeeRepository;
import com.example.hardware_softwareshopping.repository.ProductRepository;
import com.example.hardware_softwareshopping.repository.ReviewRepository;
import com.example.hardware_softwareshopping.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final ReviewRepository reviewRepository;

    public EmployeeServiceImplementation(EmployeeRepository employeeRepository, ProductRepository productRepository, CustomerRepository customerRepository, ReviewRepository reviewRepository) {
        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.reviewRepository = reviewRepository;
    }


    @Override
    public Employee findByEmailAndPassword(String email, String password) {

        return employeeRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee save(Employee employee) {
        employee.setUserRole(UserRole.EMPLOYEE);
        if (employeeRepository.findByEmail(employee.getEmail()) != null)
            return null;
        return employeeRepository.save(employee);
    }

    @Override
    public Product deleteReview(Long productID, Long reviewID) {

        Product product=productRepository.findById(productID).get();
        if(product==null)
            return null;
        Review review=reviewRepository.findById(reviewID).get();
        if(review==null)
            return null;

        product.getReviewList().removeIf(r -> r.getId().intValue() == reviewID.intValue());

         List<Customer> list=  customerRepository.findAll();

         for(Customer c:list) {
             c.getReviewList().removeIf(r -> r.getId().intValue() == reviewID.intValue());
             customerRepository.save(c);
         }
        product=productRepository.save(product);
        reviewRepository.delete(review);
        return product;
    }


}
