package com.example.hardware_softwareshopping.repository;

import com.example.hardware_softwareshopping.constants.UserRole;

import com.example.hardware_softwareshopping.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {

    Employee findByEmailAndPassword(String email,String password);

    List<Employee> findAll();

    Employee findByEmail(String email);
}
