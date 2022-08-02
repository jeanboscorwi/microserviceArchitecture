package com.jeanboscorwi.microservicesarchitecture.chat.clients;

import com.jeanboscorwi.microservicesarchitecture.chat.models.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class UserMsClient {

    WebClient webClient;

    public UserMsClient(@Value("${user-ms.url:http://localhost:8080/user-ms}") String url){
        this.webClient = WebClient.create(url);
    }

    public User getUser(Long userId){
        return webClient.get()
                .uri("/users/{userId}", userId)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }
}
