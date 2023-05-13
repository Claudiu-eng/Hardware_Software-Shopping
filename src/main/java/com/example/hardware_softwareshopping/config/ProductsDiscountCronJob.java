package com.example.hardware_softwareshopping.config;

import com.example.hardware_softwareshopping.model.Product;
import com.example.hardware_softwareshopping.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Component
@EnableScheduling
public class ProductsDiscountCronJob {

    private final ProductRepository productRepository;

    public ProductsDiscountCronJob(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Scheduled(cron = "0 59 21-22 * * *") // Rulează în fiecare zi de luni până vineri între orele 8 și 9 dimineața
    public void applyPriceDiscount() {
        List<Product> products = productRepository.findAll();
        System.out.println("Dadadq2");
        // Iterează prin produse și actualizează prețurile cu discount-ul dorit
        for (Product product : products) {
            System.out.println(product.getPrice());
            Float discountedPrice = product.getPrice()*0.9f; // Aplică un discount de 10% (prețul cu 10% mai mic)
            product.setPrice(discountedPrice);
            System.out.println(product.getPrice());
        }
        productRepository.saveAll(products);
    }

}
