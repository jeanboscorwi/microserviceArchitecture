package com.jeanboscorwi.microservicesarchitecture.user.services;

import com.jeanboscorwi.microservicesarchitecture.user.models.User;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserService {

    public static Map< Long, User> USERS_DB = Map.of(
            1L, User.builder().id(1L).name("jean bosco").build(),
            2L, User.builder().id(2L).name("Raul Gonzalez").build()
            );

    @Cacheable(value = "user-ms-users", key = "#userId")
    public User getUser(long userId) throws Exception {
        log.info("Fetching users without cache");
        //a heavy operation (3s)
        Thread.sleep(3000);
        return USERS_DB.get(userId);
    }

    @Cacheable(value = "user-ms-users")
    public Collection<User> getUsers() {
        log.info("Fetching users without cache");
        return USERS_DB.values();
    }
}
