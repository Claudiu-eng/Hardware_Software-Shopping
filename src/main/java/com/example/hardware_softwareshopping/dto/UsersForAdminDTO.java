package com.example.hardware_softwareshopping.dto;


import com.example.hardware_softwareshopping.constants.UserRole;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsersForAdminDTO {

    private String email;

    private String firstName;
    private String lastName;
    private String numberOfTelephone;

    private UserRole userRole;

}
