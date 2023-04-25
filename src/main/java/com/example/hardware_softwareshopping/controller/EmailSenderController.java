package com.example.hardware_softwareshopping.controller;

import com.example.hardware_softwareshopping.resource.EmailMassage;
import com.example.hardware_softwareshopping.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/mail")
public class EmailSenderController {

    private EmailService emailService;


    public EmailSenderController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public ResponseEntity sendEmail(@RequestBody EmailMassage emailMassage){
        emailService.sendEmail(emailMassage.getTo(),emailMassage.getSubject(),
                emailMassage.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
