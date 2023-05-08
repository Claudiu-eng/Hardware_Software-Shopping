package com.example.hardware_softwareshopping.service.implementations;

import com.example.hardware_softwareshopping.events.NewOrderEvent;
import com.example.hardware_softwareshopping.model.Customer;
import com.example.hardware_softwareshopping.model.Orders;
import com.example.hardware_softwareshopping.model.Product;
import com.example.hardware_softwareshopping.model.ShoppingCart;
import com.example.hardware_softwareshopping.repository.CustomerRepository;
import com.example.hardware_softwareshopping.repository.OrdersRepository;
import com.example.hardware_softwareshopping.repository.ProductRepository;
import com.example.hardware_softwareshopping.service.OrdersService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class OrdersServiceImplementation implements OrdersService {
    private final OrdersRepository ordersRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrdersServiceImplementation(OrdersRepository ordersRepository, ApplicationEventPublisher applicationEventPublisher, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.ordersRepository = ordersRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }


    @Override
    public Customer insertOrder(String email) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null)
            return null;
        ShoppingCart shoppingCart = customer.getShoppingCart();

        String msg = "Comanda contine =\n";
        float totalPrice=0.0f;
        for (Map.Entry<Product, Integer> m : shoppingCart.getQuantities().entrySet()) {
            Optional<Product> p = productRepository.findById(m.getKey().getId());
            if (p.get() == null)
                continue;
            if (p.get().getStock() >= m.getValue()) {
                msg = msg + String.valueOf(m.getValue())+"x "+m.getKey().getName() + " " + m.getKey().getPrice() + "\n";
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

        customer.getOrderList().add(orders);
        Customer c =customerRepository.save(customer);

        orders=c.getOrderList().get(c.getOrderList().size()-1);

        applicationEventPublisher.publishEvent(new NewOrderEvent(this,orders));

        return c;

    }
}
