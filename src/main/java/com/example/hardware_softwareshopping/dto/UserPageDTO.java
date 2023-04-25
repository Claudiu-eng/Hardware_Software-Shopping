package com.example.hardware_softwareshopping.dto;


import com.example.hardware_softwareshopping.constants.UserRole;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPageDTO {
    private String email;

    private UserRole userRole;
}
