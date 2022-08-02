package com.jeanboscorwi.microservicesarchitecture.chat.controllers;

import com.jeanboscorwi.microservicesarchitecture.chat.models.MessageRequest;
import com.jeanboscorwi.microservicesarchitecture.chat.services.ChatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
@AllArgsConstructor
public class ChatController {

    private ChatService chatService;

    @RequestMapping(path = "/message", method = {RequestMethod.POST})
    ResponseEntity<String> sendMessage(@RequestBody MessageRequest messageRequest) {
        
        chatService.sendMessage(messageRequest);

        return ResponseEntity.ok("message Sent");
    }
}
