package com.example.hardware_softwareshopping.service.implementations;

import com.example.hardware_softwareshopping.dto.ProductFilterDTO;
import com.example.hardware_softwareshopping.model.Category;
import com.example.hardware_softwareshopping.model.Product;
import com.example.hardware_softwareshopping.model.Review;
import com.example.hardware_softwareshopping.repository.CategoryRepository;
import com.example.hardware_softwareshopping.repository.ProductRepository;
import com.example.hardware_softwareshopping.service.CategoryService;
import com.example.hardware_softwareshopping.service.ProductService;
import org.springframework.stereotype.Service;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImplementation(ProductRepository productRepository, CategoryService categoryService, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;

    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {

        Optional<Category> category = categoryRepository.findById(product.getCategory().getId());
        product.setCategory(category.get());
        if(category.get()==null)
            return null;
        product.setReviewList(new ArrayList<>());
        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(Long id) {
        Optional<Product> product=productRepository.findById(id);
        productRepository.deleteById(id);
        return product.get();
    }

    @Override
    public List<Review> getReviews(Long productId) {
        Optional<Product> product=productRepository.findById(productId);
        if(product.get()==null)
            return null;
        return product.get().getReviewList();
    }

    @Override
    public List<Product> findAllByNameOrDescription(String keyWord) {

        return productRepository.findByNameContainingOrDescriptionContaining(keyWord,keyWord);
    }

    @Override
    public List<Product> findByPriceBetweenAndCategoryIn(ProductFilterDTO productFilterDTO) {

        List<Product> products = new ArrayList<>();

        List<Category> categories = productFilterDTO.getCategories().stream()
                .map(categoryRepository::findById).filter(Optional::isPresent)
                .map(Optional::get).collect(Collectors.toList());

       if(productFilterDTO.getPrices().size()==0){
           products = productRepository.findAllByCategoryIn(categories);
       }else {
           for (String prices : productFilterDTO.getPrices()) {
               double minPrice, maxPrice;
               if (prices.contains("+")) {
                   prices = prices.substring(0, prices.length() - 1);
                   minPrice = Double.parseDouble(prices);
                   maxPrice = 999999999.0;
               } else {
                   String[] price = prices.split("-");
                   minPrice = Double.parseDouble(price[0]);
                   maxPrice = Double.parseDouble(price[1]);
               }
               List<Product> productsInRange;
               if (categories.size() > 0)
                   productsInRange = productRepository.findByPriceBetweenAndCategoryIn(minPrice, maxPrice, categories);
               else productsInRange = productRepository.findAllByPriceBetween(minPrice, maxPrice);

               products.addAll(productsInRange);
           }
       }

        XMLEncoder encoder = null;
        try {
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("xmlFiles")));
        } catch (FileNotFoundException fileNotFound) {
            System.out.println("ERROR: While Creating or Opening the File dvd.xml");
        }
        if (encoder != null){
            encoder.writeObject(products);
            encoder.close();
        }

        return products;
    }
}
