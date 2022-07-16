package com.jeanboscorwi.microservicesarchitecture.user.services;

import com.jeanboscorwi.microservicesarchitecture.user.models.User;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class UserService {
    @Cacheable(value = "test")
    public List<User> getUsers() {
        log.info("Fetching users without cache");
        return List.of(
                User.builder().id(1L).name("jean bosco").build(),
                User.builder().id(2L).name("Raul Gonzalez").build()
        );
    }
}
