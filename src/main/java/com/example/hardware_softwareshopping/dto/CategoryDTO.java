package com.example.hardware_softwareshopping.dto;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    @NonNull
    @Pattern(regexp = "^[0-9]+$", message = "only digits for category selected,")
    private String id;

}
