package com.product.productservice.service;

import com.product.productservice.exceptions.ProductNotFoundException;
import com.product.productservice.models.Product;
import com.product.productservice.repositories.CategoryRepository;
import com.product.productservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("serfProductService")
public class SelfProductService implements IProdcutService{


    public ProductRepository productRepository;
    public CategoryRepository categoryRepository;


    public SelfProductService(ProductRepository productRepository,
                              CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct =  productRepository.findById(id);

        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }
        return optionalProduct.get();
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize) throws ProductNotFoundException {
        Page<Product> products =
                productRepository.findAll(PageRequest.of(pageNumber,pageSize,
                        Sort.by("price").ascending().and(Sort.by("title")).descending()));
        if(products.isEmpty()){
            throw new ProductNotFoundException("List is empty");
        }
        return products;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }
}
