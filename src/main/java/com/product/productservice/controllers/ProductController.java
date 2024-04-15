package com.product.productservice.controllers;

import com.product.productservice.models.Product;
import com.product.productservice.service.IProdcutService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return new ArrayList<>();
    }
}
