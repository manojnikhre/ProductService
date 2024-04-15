package com.product.productservice.service;

import com.product.productservice.models.Product;

import java.util.List;

public interface IProdcutService {

    Product getProductById(Long id);
    List<Product> getAllProducts();
}
