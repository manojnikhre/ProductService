package com.product.productservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends Exception{
    private Long id;
    private String message;

    public ProductNotFoundException(String message){
        super(message);
    }
}
