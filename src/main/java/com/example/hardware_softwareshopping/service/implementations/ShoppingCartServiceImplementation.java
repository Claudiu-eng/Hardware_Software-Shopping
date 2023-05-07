package com.example.hardware_softwareshopping.service.implementations;

import com.example.hardware_softwareshopping.model.Customer;
import com.example.hardware_softwareshopping.model.Product;
import com.example.hardware_softwareshopping.model.ShoppingCart;
import com.example.hardware_softwareshopping.repository.CustomerRepository;
import com.example.hardware_softwareshopping.repository.ProductRepository;
import com.example.hardware_softwareshopping.repository.ShoppingCartRepository;
import com.example.hardware_softwareshopping.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ShoppingCartServiceImplementation implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public ShoppingCartServiceImplementation(ShoppingCartRepository shoppingCartRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }


    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart insertProduct(Product product,String email) {
        //trb modificat

        Customer customer = customerRepository.findByEmail(email);
        if(customer==null)
            return null;
        ShoppingCart shoppingCart = customer.getShoppingCart();
        if(shoppingCart==null)
            return null;
        Set<Product> productSet=shoppingCart.getProducts();
        boolean q=true;
        for(Product p:productSet){
            if(p.getDescription().equals(product.getDescription()) && p.getName().equals(product.getName())) {
                q = false;

                product = p;
                break;
            }
        }

        if(!q)
            shoppingCart.getQuantities().put(product,shoppingCart.getQuantities().get(product)+1);
        else{
            shoppingCart.getProducts().add(product);
            shoppingCart.getQuantities().put(product,1);
        }
        shoppingCart.setTotalPrice(shoppingCart.getTotalPrice()+product.getPrice());


        return shoppingCartRepository.save(shoppingCart);
    }
}
