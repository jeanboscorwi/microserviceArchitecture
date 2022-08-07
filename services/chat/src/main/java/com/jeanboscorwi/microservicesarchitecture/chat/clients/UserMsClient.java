package com.jeanboscorwi.microservicesarchitecture.chat.clients;

import com.jeanboscorwi.microservicesarchitecture.chat.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@Slf4j
public class UserMsClient {

    private RestTemplate restTemplate;
    private String url;

    public UserMsClient( @Value("${user-ms.url:http://localhost:8080/user-ms}") String url, RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public User getUser(Long userId){
        return restTemplate.exchange(
                this.url+"/users/"+userId.toString(),
                HttpMethod.GET,
                null,
                User.class,
                Collections.emptyMap()).getBody();
    }
}
