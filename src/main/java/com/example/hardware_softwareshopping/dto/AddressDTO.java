package com.example.hardware_softwareshopping.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDTO {

    @NonNull
    private String city;

    @NonNull
    private String street;

    @NonNull
    @Pattern(regexp = "^[0-9]{1,5}$", message = "length of address number Must be maximum 5 DIGITS")
    private String number;

}
