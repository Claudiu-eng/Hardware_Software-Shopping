package com.example.hardware_softwareshopping;

import com.example.hardware_softwareshopping.constants.UserRole;
import com.example.hardware_softwareshopping.model.Employee;
import com.example.hardware_softwareshopping.model.User;
import com.example.hardware_softwareshopping.repository.UserRepository;
import com.example.hardware_softwareshopping.service.UserService;
import com.example.hardware_softwareshopping.service.implementations.UserServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
@SpringBootTest
class UserInsertTest {
//
//    private static final String email = "email@test";
//    private static final String password = "test";
//
//    private UserService userService;
//
//    @Mock
//    private UserRepository userRepository;
//
//    private Employee employee;
//
//    @BeforeEach
//    void setUp(){
//        initMocks(this);
//
//    }
//
//    @Test
//    public void insert(){
//        Employee employee = new Employee();
//        employee.setFirstName("test1");
//        employee.setEmail("employee@test");
//        employee.setPassword("test1");
//        //employee.setRole(UserRole.EMPLOYEE);
//        employee.setWage(143);
//        userService = new UserServiceImplementation(userRepository);
//
//        when(userRepository.save(employee)).thenReturn(employee);
//
//        User inserted = userService.save(employee);
//
//        assertNotNull(inserted);
//        assertEquals(inserted.getEmail(),"employee@test");
//
//
//    }
//
//    @Test
//    public void delete(){
//        Employee employee = new Employee();
//        employee.setFirstName("test1");
//        employee.setEmail("employee@test");
//        employee.setPassword("test1");
//        //employee.setRole(UserRole.EMPLOYEE);
//        employee.setWage(143);
//        userService = new UserServiceImplementation(userRepository);
//
//        when(userRepository.save(employee)).thenReturn(employee);
//
//
//        User inserted = userService.save(employee);
//
//        List<User> userList = userService.findAllByRole(UserRole.EMPLOYEE);
//        System.out.println(userList.size());
//
//        assertNotNull(inserted);
//        assertEquals(inserted.getEmail(),"employee@test");
//
//        doNothing().when(userRepository).deleteById(inserted.getId());
//
//        userService.deleteById(inserted.getId());
//
//        User user2 = userService.findByEmailAndPassword("employee@test","test1");
//        assertNull(user2);
//
//        System.out.println("DELETED");
//        List<User> userList1 = userService.findAllByRole(UserRole.EMPLOYEE);
//
//
//    }
//
//    @Test
//    public void update(){
//        Employee employee = new Employee();
//        employee.setFirstName("test1");
//        employee.setEmail("employee@test");
//        employee.setPassword("test1");
//        //employee.setRole(UserRole.EMPLOYEE);
//        employee.setWage(143);
//        userService = new UserServiceImplementation(userRepository);
//
//        when(userRepository.save(employee)).thenReturn(employee);
//
//        User inserted = userService.save(employee);
//
//        assertNotNull(inserted);
//        assertEquals(inserted.getEmail(),"employee@test");
//
//        when(userRepository.findById(inserted.getId())).thenReturn(Optional.of(inserted));
//
//
//        inserted.setEmail("schimbat");
//
//        User updated = userService.save(inserted);
//
//        assertEquals(updated.getEmail(),"schimbat");
//        assertEquals(updated.getId(),inserted.getId());
//
//    }
//
//




}
