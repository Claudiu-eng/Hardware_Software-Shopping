package com.example.hardware_softwareshopping.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@XmlRootElement(name="product")
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "description")
    private String description;

    @XmlTransient
    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<Review> reviewList;

    @XmlElement(name = "stock")
    private int stock;

    @XmlElement(name = "price")
    private float price;

    @XmlElement(name = "category")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @XmlTransient
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<ShoppingCart> shoppingCarts;



}
