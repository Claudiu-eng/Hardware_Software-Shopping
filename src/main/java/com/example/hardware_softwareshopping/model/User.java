package com.example.hardware_softwareshopping.model;

import com.example.hardware_softwareshopping.constants.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String email;
    private String password;

    private String code;

    @Size(min = 3,max = 40,message = "size must be between {min} and {max}")
    private String firstName;
    private String lastName;

    private String numberOfTelephone;

    private UserRole userRole;

    private boolean isConnected;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<LocalDateTime> loginTimes;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<LocalDateTime> logoutTimes;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", numberOfTelephone='" + numberOfTelephone + '\'' +
                '}';
    }

}
