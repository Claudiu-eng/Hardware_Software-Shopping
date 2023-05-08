package com.example.hardware_softwareshopping.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Customer extends User{

    private Integer bonusCredit;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private ShoppingCart shoppingCart;

    @Override
    public String toString() {
        return "Customer{" +
                "bonusCredit=" + bonusCredit +
                ", shoppingCart=" + shoppingCart +
                ", notificationList=" + notificationList +
                ", reviewList=" + reviewList +
                '}';
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Notification> notificationList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Orders> orderList;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Review> reviewList;




}
