package com.example.hardware_softwareshopping.model;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee extends User{

    private int wage;

    @Override
    public String toString() {
        return "Employee{" +
                "wage=" + wage +
                '}';
    }
}
