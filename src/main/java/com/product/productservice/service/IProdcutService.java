package com.product.productservice.service;

import com.product.productservice.exceptions.ProductNotFoundException;
import com.product.productservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProdcutService {

    Product getProductById(Long id) throws ProductNotFoundException;
    Page<Product> getAllProducts(int pageNumber, int pageSize) throws ProductNotFoundException;

    Product replaceProduct(Long id, Product product);
}
