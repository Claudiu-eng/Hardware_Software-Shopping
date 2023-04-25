package com.example.hardware_softwareshopping.model;


import com.example.hardware_softwareshopping.constants.UserRole;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String password;

    private String firstName;
    private String lastName;
    private String numberOfTelephone;

    private UserRole userRole;

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
