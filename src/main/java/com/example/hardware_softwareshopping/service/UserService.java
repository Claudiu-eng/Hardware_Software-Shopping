package com.example.hardware_softwareshopping.service;

import com.example.hardware_softwareshopping.constants.UserRole;
import com.example.hardware_softwareshopping.dto.AuthDTO;
import com.example.hardware_softwareshopping.dto.UserPageDTO;
import com.example.hardware_softwareshopping.dto.UsersForAdminDTO;
import com.example.hardware_softwareshopping.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {

    UserPageDTO findByEmailAndPassword(AuthDTO authDTO);


    User save(User user);

    List<UsersForAdminDTO> findAll();

    User findByEmail(String email);

    User deleteByEmail(String email);

    Optional<User> findById(Long id);
}