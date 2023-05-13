package com.example.hardware_softwareshopping.service.implementations;

import com.example.hardware_softwareshopping.constants.OrderStatus;
import com.example.hardware_softwareshopping.constants.NotificationEndPoints;
import com.example.hardware_softwareshopping.events.NewOrderEvent;
import com.example.hardware_softwareshopping.exceptions.ApiExceptionResponse;
import com.example.hardware_softwareshopping.model.*;
import com.example.hardware_softwareshopping.repository.CustomerRepository;
import com.example.hardware_softwareshopping.repository.OrdersRepository;
import com.example.hardware_softwareshopping.repository.ProductRepository;
import com.example.hardware_softwareshopping.service.OrdersService;
import org.aspectj.weaver.ast.Or;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
public class OrdersServiceImplementation implements OrdersService {
    private final OrdersRepository ordersRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public OrdersServiceImplementation(OrdersRepository ordersRepository, ApplicationEventPublisher applicationEventPublisher, CustomerRepository customerRepository, ProductRepository productRepository, SimpMessagingTemplate simpMessagingTemplate) {
        this.ordersRepository = ordersRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    @Override
    public Customer insertOrder(String email) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null)
            return null;
        ShoppingCart shoppingCart = customer.getShoppingCart();

        String msg = "Comanda contine ="+'\n';
        float totalPrice=0.0f;
        for (Map.Entry<Product, Integer> m : shoppingCart.getQuantities().entrySet()) {
            Optional<Product> p = productRepository.findById(m.getKey().getId());
            if (p.get() == null)
                continue;
            if (p.get().getStock() >= m.getValue()) {
                msg = msg + String.valueOf(m.getValue())+"x "+m.getKey().getName() + " " + m.getKey().getPrice() + '\n';
                p.get().setStock(p.get().getStock()-m.getValue());
                productRepository.save(p.get());
                totalPrice += m.getValue()*p.get().getPrice();
            }
        }
        msg = msg + "Total = " + totalPrice;
        shoppingCart.getProducts().clear();
        shoppingCart.getQuantities().clear();
        shoppingCart.setTotalPrice(0.0f);
        Orders orders = new Orders();
        orders.setMessage(msg);
        orders.setOrderStatus(OrderStatus.WAITING);

        customer.getOrderList().add(orders);
        Customer c =customerRepository.save(customer);

        orders=c.getOrderList().get(c.getOrderList().size()-1);

        applicationEventPublisher.publishEvent(new NewOrderEvent(this,orders));

        return c;

    }

    @Override
    public Orders rejectOrder(Long id,String email) throws ApiExceptionResponse {
        Optional<Orders> orders = ordersRepository.findById(id);
        Customer customer = customerRepository.findByEmail(email);
        if(orders.get()==null || customer==null)
            throw ApiExceptionResponse.builder().status(HttpStatus.NOT_FOUND).message("null order").errors(Collections.singletonList("error.addrs.not_found")).build();
        if(orders.get().getOrderStatus()!=OrderStatus.WAITING)
            throw ApiExceptionResponse.builder().status(HttpStatus.NOT_FOUND).message("order processed already").errors(Collections.singletonList("error.addrs.not_found")).build();

        Orders orders1 = orders.get();

        this.simpMessagingTemplate.convertAndSend(
                "/topic/socket/notifications/"+email,"order with id = "+orders1.getId()+
                " was rejected");

        orders1.setOrderStatus(OrderStatus.REJECTED);
        return ordersRepository.save(orders1);
    }

    @Override
    public Orders validateOrder(Long id,String email) throws ApiExceptionResponse {
        Optional<Orders> orders = ordersRepository.findById(id);
        if(orders.get()==null)
            throw ApiExceptionResponse.builder().status(HttpStatus.NOT_FOUND).message("null order").errors(Collections.singletonList("error.addrs.not_found")).build();
        if(orders.get().getOrderStatus()!=OrderStatus.WAITING)
            throw ApiExceptionResponse.builder().status(HttpStatus.NOT_FOUND).message("order processed already").errors(Collections.singletonList("error.addrs.not_found")).build();

        Customer customer = customerRepository.findByEmail(email);
        Orders orders1 = orders.get();


        this.simpMessagingTemplate.convertAndSend(
                "/topic/socket/notifications/"+email,"order with id = "+orders1.getId()+
                        " was validated");

        orders1.setOrderStatus(OrderStatus.VALIDATED);
        return ordersRepository.save(orders1);
    }
}
