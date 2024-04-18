package com.product.productservice.service;

import com.product.productservice.dtos.FakeStoreProductDTO;
import com.product.productservice.exceptions.ProductNotFoundException;
import com.product.productservice.models.Category;
import com.product.productservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
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
    public Product getProductById(Long id) throws ProductNotFoundException {
        //Call fake store API here to get the product with given id
        FakeStoreProductDTO fakeStoreProductDTO =
                restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDTO.class);
        //Convert FakeStoreProductDTO into Product object
        if(fakeStoreProductDTO == null){
            throw  new ProductNotFoundException(id,"Product with "+id+"  not found");
        }
        return convertFakeStoreDtoToProduct(fakeStoreProductDTO);
    }

    @Override
    public List<Product> getAllProducts() {
        // call fake store API to get all the product.
        FakeStoreProductDTO[] fakeStoreProductDTOS =
                restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);

        //convert fakestoreproductdto into product object
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOS){
            products.add(convertFakeStoreDtoToProduct(fakeStoreProductDTO));
        }
        return products;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {

        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId(id);
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setImage(product.getImage());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setCategory("jewelry");


        RequestCallback requestCallback = restTemplate.httpEntityCallback(id, FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor =
                new HttpMessageConverterExtractor(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO response =
                restTemplate.execute("https://fakestoreapi.com/products"+id, HttpMethod.PUT, requestCallback, responseExtractor);
        return convertFakeStoreDtoToProduct(response);
    }
}
