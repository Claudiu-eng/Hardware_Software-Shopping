package com.example.hardware_softwareshopping.service;

import org.springframework.stereotype.Component;


@Component
public interface EmailService {

    void sendEmail(String to,String subject,String massage);


}
