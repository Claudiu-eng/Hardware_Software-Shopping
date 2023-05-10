package com.example.hardware_softwareshopping.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSearchedDTO {

    private String email;
    private List<String> roles;

}
