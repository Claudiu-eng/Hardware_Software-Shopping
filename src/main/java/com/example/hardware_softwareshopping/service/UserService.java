package com.example.hardware_softwareshopping.service;

import com.example.hardware_softwareshopping.constants.UserRole;
import com.example.hardware_softwareshopping.dto.*;
import com.example.hardware_softwareshopping.exceptions.ApiExceptionResponse;
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

    UserPageDTO sendCode(String email) throws ApiExceptionResponse;

    UserPageDTO resetPassword(UserResetPasswordDTO userResetPasswordDTO) throws ApiExceptionResponse;

    UserPageDTO logOut(String email);

    Integer totalNumberOfConnectedUsers();

    List<UsersForAdminDTO> searchUsers(UserSearchedDTO userSearchedDTO);

    UserActivityDTO getUserActivity(String email);
}
