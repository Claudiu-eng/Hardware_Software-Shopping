package com.example.hardware_softwareshopping.repository;

import com.example.hardware_softwareshopping.constants.UserRole;
import com.example.hardware_softwareshopping.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    User findByEmailAndPassword(String email,String password);

    List<User> findAll();

    User save(User user);

    User findByEmail(String email);

    Optional<User> findById(Long id);

    List<User> findByIsConnected(boolean isConnected);

    List<User> findByEmailContainingOrUserRoleIn(String email, List<UserRole> userRoles);


}
