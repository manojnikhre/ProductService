package com.product.productservice.commons;


import com.product.productservice.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommons {

    @Autowired
    private RestTemplate restTemplate;

    public UserDto validateToken(String tokenValue){
        //Call the User Service to validate Token
        ResponseEntity<UserDto> responseEntity =
        restTemplate.getForEntity("http://localhost:8080/users/validate/"+tokenValue
                ,UserDto.class);
        if(responseEntity.getBody() == null){
            //Token is invalid
            //Throw some error
            return null;
        }
        return responseEntity.getBody();
    }
}
