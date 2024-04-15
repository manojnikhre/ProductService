package com.product.productservice.service;

import com.product.productservice.dtos.FakeStoreProductDTO;
import com.product.productservice.models.Category;
import com.product.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProdcutService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreDtoToProduct(FakeStoreProductDTO dto){
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setImage(dto.getImage());

        Category category = new Category();
        category.setDescription(dto.getCategory());
        product.setCategory(category);
        return product;
    }
    @Override
    public Product getProductById(Long id) {
        //Call fake store API here to get the product with given id
        FakeStoreProductDTO fakeStoreProductDTO =
                restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDTO.class);
        //Convert FakeStoreProductDTO into Product object
        if(fakeStoreProductDTO == null){
            return null;
        }
        return convertFakeStoreDtoToProduct(fakeStoreProductDTO);
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }
}
