package com.example.hardware_softwareshopping.model;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Admin extends User{
    @Override
    public String toString() {
        return "Admin{" +
                "age=" + age +
                '}';
    }

    private int age;

}
