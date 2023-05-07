package com.example.hardware_softwareshopping.events;

import com.example.hardware_softwareshopping.model.Orders;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
public class NewOrderEvent extends ApplicationEvent {

    private Orders orders;

    public NewOrderEvent(final Object source, final Orders orders) {
        super(source);
        this.orders = orders;
    }


}
