package com.example.hardware_softwareshopping.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserResetPasswordDTO {

    private String email;
    private String password;
    private String confirmPassword;
    private String code;

}
