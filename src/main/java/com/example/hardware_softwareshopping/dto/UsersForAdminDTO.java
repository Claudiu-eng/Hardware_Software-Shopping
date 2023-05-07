package com.example.hardware_softwareshopping.dto;


import com.example.hardware_softwareshopping.constants.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsersForAdminDTO {

    private String email;

    private String firstName;
    private String lastName;
    private String numberOfTelephone;

    private UserRole userRole;

}
