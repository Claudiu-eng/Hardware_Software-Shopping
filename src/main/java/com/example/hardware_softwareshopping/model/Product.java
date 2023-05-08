package com.example.hardware_softwareshopping.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<Review> reviewList;

    private int stock;

    private float price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<ShoppingCart> shoppingCarts;



}
