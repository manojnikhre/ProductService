package com.product.productservice.service;

import com.product.productservice.exceptions.ProductNotFoundException;
import com.product.productservice.models.Product;

import java.util.List;

public interface IProdcutService {

    Product getProductById(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();

    Product replaceProduct(Long id, Product product);
}
