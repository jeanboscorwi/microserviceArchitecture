package com.jeanboscorwi.microservicesarchitecture.chat.services;

import com.jeanboscorwi.microservicesarchitecture.chat.clients.NotificationProducer;
import com.jeanboscorwi.microservicesarchitecture.chat.clients.UserMsClient;
import com.jeanboscorwi.microservicesarchitecture.chat.models.MessageRequest;
import com.jeanboscorwi.microservicesarchitecture.chat.models.NotificationRequest;
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
    private NotificationProducer notificationProducer;

    public void sendMessage(MessageRequest messageRequest) throws Exception{

        User recipient = this.userMsClient.getUser(messageRequest.getRecipientId());
        if(recipient != null){
            NotificationRequest notificationRequest = NotificationRequest.builder()
                    .deviceId(recipient.getDeviceId())
                    .payload(String.format("New message to %s", recipient.getName()))
                    .build();

            notificationProducer.sendMessage(notificationRequest);
        }
    }
}
