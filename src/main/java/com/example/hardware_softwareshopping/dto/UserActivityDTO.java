package com.example.hardware_softwareshopping.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserActivityDTO {

    private List<LocalDateTime> logInS;
    private List<LocalDateTime> logOutS;

}
