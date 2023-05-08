package com.example.hardware_softwareshopping.dto;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    @Pattern(regexp = "^[0-9]{1,7}$", message = "stock is only digits,maximum 7 digits")
    private String stock;

    @NonNull
    @Pattern(regexp="^[0-9]+.?[0-9]+",message = "invalid float number")
    private String price;

    @NonNull
    private CategoryDTO category;
}
