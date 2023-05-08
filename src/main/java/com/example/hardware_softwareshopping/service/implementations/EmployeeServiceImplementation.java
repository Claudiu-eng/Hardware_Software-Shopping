package com.example.hardware_softwareshopping.service.implementations;

import com.example.hardware_softwareshopping.constants.UserRole;
import com.example.hardware_softwareshopping.dto.AddressDTO;
import com.example.hardware_softwareshopping.dto.CustomerDTO;
import com.example.hardware_softwareshopping.dto.EmployeeDTO;
import com.example.hardware_softwareshopping.exceptions.ApiExceptionResponse;
import com.example.hardware_softwareshopping.model.Customer;
import com.example.hardware_softwareshopping.model.Employee;
import com.example.hardware_softwareshopping.model.Product;
import com.example.hardware_softwareshopping.model.Review;
import com.example.hardware_softwareshopping.repository.*;
import com.example.hardware_softwareshopping.service.EmployeeService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public EmployeeServiceImplementation(EmployeeRepository employeeRepository, ProductRepository productRepository, CustomerRepository customerRepository, ReviewRepository reviewRepository, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
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
    public Employee save(EmployeeDTO employeeDTO) throws ApiExceptionResponse{

        if (userRepository.findByEmail(employeeDTO.getEmail()) != null)
            throw ApiExceptionResponse.builder().status(HttpStatus.NOT_FOUND).message("email already exist").errors(Collections.singletonList("error.addrs.not_found")).build();

        Validator validator  = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(employeeDTO);
        if (!violations.isEmpty()) {
            String msg = "";
            for (ConstraintViolation<EmployeeDTO> violation : violations) {
                msg+=violation.getMessage();
            }
            throw ApiExceptionResponse.builder().status(HttpStatus.NOT_FOUND).message(msg).errors(Collections.singletonList("error.addrs.not_found")).build();
        }
        Employee employee = new Employee();
        employee.setWage(Integer.parseInt(employeeDTO.getWage()));
        employee.setPassword(employeeDTO.getPassword());
        employee.setEmail(employeeDTO.getEmail());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employee.getLastName());
        employee.setNumberOfTelephone(employeeDTO.getNumberOfTelephone());
        employee.setUserRole(UserRole.EMPLOYEE);
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
