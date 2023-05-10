package com.example.hardware_softwareshopping.dto;

import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDTO {

    @NonNull
    @Email(message = "email invalid")
    private String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Invalid password format," +
                    "insert at least 8 chars,1 digit 1 lowercase letter,1 uppercase letter" +
                    "not white spaces and a special character (@#$%^&+=)")
    private String password;


    @NonNull
    @Pattern(regexp = "^[A-Z][a-z\s]*$",message = "first letter of firstName" +
            "must be upercase")
    @Size(min = 3,max = 20,message = "size first name must be between {min} and {max}")
    private String firstName;

    @NonNull
    @Pattern(regexp = "^[A-Z][a-z\s]*$",message = "first letter of lastName" +
            "must be upercase")
    @Size(min = 3,max = 20,message = "size last name must be between {min} and {max}")
    private String lastName;

    @NonNull
    @Pattern(regexp = "^0[0-9]{9}$", message = "No of mobile Must be 10 DIGITS and start with 0")
    private String numberOfTelephone;

    private AddressDTO address;


}
