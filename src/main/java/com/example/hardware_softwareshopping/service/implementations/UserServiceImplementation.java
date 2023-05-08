package com.example.hardware_softwareshopping.service.implementations;


import com.example.hardware_softwareshopping.dto.*;

import com.example.hardware_softwareshopping.events.NewOrderEvent;
import com.example.hardware_softwareshopping.events.NewResetPasswordEvent;
import com.example.hardware_softwareshopping.exceptions.ApiExceptionResponse;
import com.example.hardware_softwareshopping.model.User;

import com.example.hardware_softwareshopping.repository.UserRepository;
import com.example.hardware_softwareshopping.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public UserServiceImplementation(UserRepository userRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.userRepository = userRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    @Override
    public UserPageDTO findByEmailAndPassword(AuthDTO authDTO) {

        User user= userRepository.findByEmailAndPassword(authDTO.getEmail(),authDTO.getPassword());
        if(user==null)
            return null;
        user.setConnected(true);
        user.getLoginTimes().add(LocalDateTime.now());
        userRepository.save(user);
        UserPageDTO userPageDTO = new UserPageDTO();
        userPageDTO.setEmail(authDTO.getEmail());
        userPageDTO.setUserRole(user.getUserRole());
        return userPageDTO;
    }

    @Override
    public User save(User user) {

        if(userRepository.findByEmail(user.getEmail())!=null)
            return null;
        User u =userRepository.save(user);
        return u;
    }

    @Override
    public List<UsersForAdminDTO> findAll() {

        List<User> users = userRepository.findAll();

        List<UsersForAdminDTO> list = new ArrayList<>();
        for(User user:users){
            UsersForAdminDTO usersForAdminDTO = new UsersForAdminDTO();
            usersForAdminDTO.setEmail(user.getEmail());
            usersForAdminDTO.setFirstName(user.getFirstName());
            usersForAdminDTO.setLastName(user.getLastName());
            usersForAdminDTO.setNumberOfTelephone(user.getNumberOfTelephone());
            usersForAdminDTO.setUserRole(user.getUserRole());
            list.add(usersForAdminDTO);
        }

        return list;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User deleteByEmail(String email) {

        User user = userRepository.findByEmail(email);
        userRepository.delete(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserPageDTO sendCode(String email) throws ApiExceptionResponse{
        User user=userRepository.findByEmail(email);
        if(user==null)
            throw ApiExceptionResponse.builder().status(HttpStatus.NOT_FOUND).message("email invalid").errors(Collections.singletonList("error.addrs.not_found")).build();
        String randomString = UUID.randomUUID().toString().substring(0,8);
        user.setCode(randomString);
        userRepository.save(user);
        UserPageDTO userPageDTO = new UserPageDTO();
        userPageDTO.setUserRole(user.getUserRole());
        userPageDTO.setEmail(email);
        applicationEventPublisher.publishEvent(new NewResetPasswordEvent(this,user));
        return userPageDTO;
    }

    @Override
    public UserPageDTO resetPassword(UserResetPasswordDTO userResetPasswordDTO) throws ApiExceptionResponse {

        User user = userRepository.findByEmail(userResetPasswordDTO.getEmail());
        if(user==null)
            throw ApiExceptionResponse.builder().status(HttpStatus.NOT_FOUND).message("email invalid/not found").errors(Collections.singletonList("error.addrs.not_found")).build();


        Validator validator  = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UserResetPasswordDTO>> violations = validator.validate(userResetPasswordDTO);
        if (!violations.isEmpty()) {
            String msg = "";
            for (ConstraintViolation<UserResetPasswordDTO> violation : violations) {
                msg+=violation.getMessage();
            }
            throw ApiExceptionResponse.builder().status(HttpStatus.NOT_FOUND).message(msg).errors(Collections.singletonList("error.addrs.not_found")).build();
        }

        if(!userResetPasswordDTO.getPassword().equals(
                userResetPasswordDTO.getConfirmPassword())) {
            user.setCode("");
            userRepository.save(user);
            throw ApiExceptionResponse.builder().status(HttpStatus.NOT_FOUND).message("parolele nu coincid,recereti cod").errors(Collections.singletonList("error.addrs.not_found")).build();

        }
        if(!user.getCode().equals(userResetPasswordDTO.getCode())) {
            user.setCode("");
            userRepository.save(user);
            throw ApiExceptionResponse.builder().status(HttpStatus.NOT_FOUND).message("codurile nu coincid,recereti cod").errors(Collections.singletonList("error.addrs.not_found")).build();
        }

        UserPageDTO userPageDTO = UserPageDTO.builder()
                .userRole(user.getUserRole()).email(user.getEmail())
                .build();
        user.setPassword(userResetPasswordDTO.getPassword());
        user.setCode("");
        userRepository.save(user);
        return userPageDTO;
    }

    @Override
    public UserPageDTO logOut(String email) {
        User user = userRepository.findByEmail(email);
        if(user==null)
            return null;
        user.setConnected(false);
        user.getLogoutTimes().add(LocalDateTime.now());
        userRepository.save(user);
        return UserPageDTO.builder().email(email).userRole(user.getUserRole()).build();
    }

    @Override
    public Integer totalNumberOfConnectedUsers() {
        return userRepository.findByIsConnected(true).size();
    }

    @Override
    public UserActivityDTO getUserActivity(String email) {
        User user = userRepository.findByEmail(email);
        if(user==null)
            return null;
        UserActivityDTO userActivityDTO = UserActivityDTO.builder().logInS(user.getLoginTimes()).logOutS(user.getLogoutTimes()).build();
        return userActivityDTO;
    }

}
