package com.example.hardware_softwareshopping.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    private int numberOfStars;

    private String message;




}
