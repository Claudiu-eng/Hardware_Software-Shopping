package com.example.hardware_softwareshopping.events;

import com.example.hardware_softwareshopping.model.Customer;
import com.example.hardware_softwareshopping.model.Orders;
import com.example.hardware_softwareshopping.service.CustomerService;
import com.example.hardware_softwareshopping.service.EmailService;
import com.example.hardware_softwareshopping.service.OrdersService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationHandler implements ApplicationListener<NewOrderEvent> {

    private final EmailService emailService;

    private final OrdersService ordersService;

    private final CustomerService customerService;

    public NotificationHandler(EmailService emailService, OrdersService ordersService, CustomerService customerService) {
        this.emailService = emailService;
        this.ordersService = ordersService;
        this.customerService = customerService;
    }

    @Override
    public void onApplicationEvent(NewOrderEvent event) {

        System.out.println("lalalalalala");
        Orders orders = event.getOrders();
        List<Customer> customers=customerService.findAll();
        Customer customer=new Customer();
        for(Customer c:customers){
            for(Orders o:c.getOrderList())
                if(o.getId().intValue()==orders.getId().intValue()){
                    customer=c;
                    break;
                }
        }
        System.out.println(customer.getEmail());
        System.out.println("sunt aici");
        String to="tulbureclaudiu2001@gmail.com";
        String subject="Order details";
        String body = orders.getMessage();
        emailService.sendEmail(to,subject,body);

    }
}
