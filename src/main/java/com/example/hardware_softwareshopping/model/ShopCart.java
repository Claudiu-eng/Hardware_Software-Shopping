package com.example.hardware_softwareshopping.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopCart {

    private List<Product> products;
    private List<Integer> quantities;

}
