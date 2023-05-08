package com.example.hardware_softwareshopping.controller;


import com.example.hardware_softwareshopping.dto.NameOrDescriptionDTO;
import com.example.hardware_softwareshopping.dto.ProductDTO;
import com.example.hardware_softwareshopping.dto.ProductFilterDTO;
import com.example.hardware_softwareshopping.exceptions.ApiExceptionResponse;
import com.example.hardware_softwareshopping.model.Product;
import com.example.hardware_softwareshopping.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/product_page")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("search_products_after_name_or_description")
    public ResponseEntity searchProductsFilteredByNameOrDescription(@RequestBody NameOrDescriptionDTO nameOrDescription){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAllByNameOrDescription(nameOrDescription.getKeyWord()));
    }

    @GetMapping("/see_products")
    public ResponseEntity getProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @DeleteMapping("/delete_product/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id){

        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(Long.valueOf(id)));

    }

    @PostMapping("/insert_product")
    public ResponseEntity insertProduct(@RequestBody ProductDTO product) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(productService.save(product));

    }

    @PostMapping("/filter_products")
    public ResponseEntity filterProducts(@RequestBody ProductFilterDTO productFilterDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(productService.findByPriceBetweenAndCategoryIn(productFilterDTO));

    }

}
