package com.example.hardware_softwareshopping.controller;


import com.example.hardware_softwareshopping.dto.UserSearchedDTO;
import com.example.hardware_softwareshopping.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user_page")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/see_activity/{email}")
    public ResponseEntity seeUserActivity(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserActivity(email));
    }

    @PostMapping("/search_user")
    public ResponseEntity searchUser(@RequestBody UserSearchedDTO userSearchedDTO){
        return ResponseEntity.status(HttpStatus.OK).body(userService.searchUsers(userSearchedDTO));
    }

}
