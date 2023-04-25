package com.example.hardware_softwareshopping.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface EmailService {

    void sendEmail(String to,String subject,String massage);


}
