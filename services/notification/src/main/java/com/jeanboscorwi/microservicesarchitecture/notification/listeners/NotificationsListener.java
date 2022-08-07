package com.jeanboscorwi.microservicesarchitecture.notification.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeanboscorwi.microservicesarchitecture.notification.models.NotificationRequest;
import com.jeanboscorwi.microservicesarchitecture.notification.models.RedisCommand;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationsListener {

    @Autowired
    private ObjectMapper objectMapper;
    
    public void handleMessage(String command) throws Exception {

        var receivedCommand  = objectMapper.readValue(command, new TypeReference<RedisCommand<NotificationRequest>>() {
        });
        var notificationRequest = receivedCommand.getPayload();
        var traceId = receivedCommand.getXB3TraceId();

        MDC.put("traceId", traceId);

        log.info("=> received the notification request: {}", notificationRequest.getPayload());
        Thread.sleep(15);
        log.info("<= the notification is well sent to the device : {}", notificationRequest.getDeviceId());
    }
}
