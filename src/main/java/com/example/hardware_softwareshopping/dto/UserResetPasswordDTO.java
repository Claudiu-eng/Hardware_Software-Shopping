package com.example.hardware_softwareshopping.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserResetPasswordDTO {

    @NonNull
    @Email(message = "email .....")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Invalid password format," +
                    "insert at least 8 chars,1 digit 1 lowercase letter,1 uppercase letter" +
                    "not white spaces and a special character (@#$%^&+=)")
    private String password;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Invalid password format," +
                    "insert at least 8 chars,1 digit 1 lowercase letter,1 uppercase letter" +
                    "not white spaces and a special character (@#$%^&+=)")
    private String confirmPassword;

    private String code;

}
