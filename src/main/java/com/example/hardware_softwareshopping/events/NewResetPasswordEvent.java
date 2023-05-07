package com.example.hardware_softwareshopping.events;

import com.example.hardware_softwareshopping.dto.UserPageDTO;
import com.example.hardware_softwareshopping.model.Orders;
import com.example.hardware_softwareshopping.model.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class NewResetPasswordEvent extends ApplicationEvent {

    private User user;

    public NewResetPasswordEvent(final Object source, final User user) {
        super(source);
        this.user = user;
    }
}
