package com.product.productservice.controllers;

//import com.product.productservice.commons.AuthCommons;
import com.product.productservice.commons.AuthCommons;
import com.product.productservice.dtos.UserDto;
import com.product.productservice.exceptions.ProductNotFoundException;
import com.product.productservice.models.Product;
import com.product.productservice.service.IProdcutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


//localhost:8080/products
@RestController //This controller is going to use rest REST HTTP API's
@RequestMapping("/products")
public class ProductController {

    private IProdcutService productService;

    @Autowired
    private AuthCommons authCommons;

    public ProductController(@Qualifier("serfProductService") IProdcutService productService){
        this.productService = productService;
    }

    //localhost:8080/products/1
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws ProductNotFoundException {
        //Call UserService validatetoken API to validate the token
       // UserDto userDto = authCommons.validateToken(token);

        ResponseEntity<Product> response;
       /* if(userDto == null){
            response = new ResponseEntity<>(HttpStatus.FORBIDDEN);
            return response;
        }*/

        Product product = productService.getProductById(id);

        response = new ResponseEntity<>(product,HttpStatus.OK);
        return response;
    }

    @GetMapping()
    public Page<Product> getAllProducts(@RequestParam("pageNumber") int pageNumber,
                                        @RequestParam("pageSize") int pageSize) throws ProductNotFoundException {
        return productService.getAllProducts(pageNumber,pageSize);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable Long id, @RequestBody Product product){
        return productService.replaceProduct(id,product);
    }
}
