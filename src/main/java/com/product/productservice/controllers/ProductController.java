package com.product.productservice.controllers;

import com.product.productservice.exceptions.ProductNotFoundException;
import com.product.productservice.models.Product;
import com.product.productservice.service.IProdcutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


//localhost:8080/products
@RestController //This controller is going to use rest REST HTTP API's
@RequestMapping("/products")
public class ProductController {

    private IProdcutService productService;

    public ProductController(IProdcutService productService){
        this.productService = productService;
    }

    //localhost:8080/products/1
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws ProductNotFoundException {

        Product product = productService.getProductById(id);
        ResponseEntity<Product> response;
        if(product == null){
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return response;
        }

        response = new ResponseEntity<>(product,HttpStatus.OK);
        return response;
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable Long id, @RequestBody Product product){
        return productService.replaceProduct(id,product);
    }
}
