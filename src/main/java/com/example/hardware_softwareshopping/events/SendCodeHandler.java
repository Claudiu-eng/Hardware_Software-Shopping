package com.example.hardware_softwareshopping.events;

import com.example.hardware_softwareshopping.model.User;
import com.example.hardware_softwareshopping.service.CustomerService;
import com.example.hardware_softwareshopping.service.OrdersService;
import com.example.hardware_softwareshopping.service.implementations.EmailServiceImplementation;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class SendCodeHandler implements ApplicationListener<NewResetPasswordEvent> {

    private final EmailServiceImplementation emailService;

    public SendCodeHandler(EmailServiceImplementation emailService, OrdersService ordersService, CustomerService customerService) {
        this.emailService = emailService;
    }

    @Override
    public void onApplicationEvent(NewResetPasswordEvent event) {

        User user = event.getUser();
        System.out.println(user.getEmail());
        System.out.println(user.getCode());
        String to="tulbureclaudiu2001@gmail.com";
        String subject="Code password reset";
        String body = user.getCode();
        emailService.sendEmail(to,subject,body);

    }
}
