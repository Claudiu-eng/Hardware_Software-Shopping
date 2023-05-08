package com.example.hardware_softwareshopping.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String name;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<Product> products;
}
