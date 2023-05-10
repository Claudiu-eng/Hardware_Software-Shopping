package com.example.hardware_softwareshopping.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDTO {

    @NonNull
    @Pattern(regexp = "^[A-Z][a-z]*(\s[A-Z][a-z]*)*$",message = "first letter of city" +
            "must be upercase")
    @Size(min = 3,max = 20,message = "size city must be between {min} and {max}")
    private String city;

    @NonNull
    @Size(min = 3,max = 20,message = "size street must be between {min} and {max}")
    @Pattern(regexp = "^[A-Z][a-z]*(\s[A-Z][a-z]*)*$",message = "first letter of street" +
            "must be upercase")
    private String street;

    @NonNull
    @Pattern(regexp = "^[0-9]{1,5}$", message = "length of address number Must be maximum 5 DIGITS")
    private String number;

}
