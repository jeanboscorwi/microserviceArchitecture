package com.jeanboscorwi.microservicesarchitecture.chat.services;

import com.jeanboscorwi.microservicesarchitecture.chat.clients.UserMsClient;
import com.jeanboscorwi.microservicesarchitecture.chat.models.MessageRequest;
import com.jeanboscorwi.microservicesarchitecture.chat.models.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ChatService {

    private UserMsClient userMsClient;

    public void sendMessage(MessageRequest messageRequest) {

        User recipient = this.userMsClient.getUser(messageRequest.getRecipientId());
        if(recipient != null){
            log.info("Message : {} is going to be sent to {}", messageRequest.getMessage(), recipient.getName());
        }
    }
}
