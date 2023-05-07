package com.example.hardware_softwareshopping.dto;


import com.example.hardware_softwareshopping.constants.UserRole;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserPageDTO {
    private String email;

    private UserRole userRole;
}
