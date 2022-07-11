package com.jeanboscorwi.microservicesarchitecture.user.controllers;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.Slf4j.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class UserController {

    @RequestMapping( method = {RequestMethod.GET})
    ResponseEntity<String> getUsers() {
        log.info("request received");
        return ResponseEntity.ok("yes microservice is running");
    }
}
