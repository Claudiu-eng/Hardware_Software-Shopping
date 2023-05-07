package com.example.hardware_softwareshopping.dto;

import com.example.hardware_softwareshopping.model.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopCartDTO {

    private List<Product> products;
    private List<Integer> quantities;

}
