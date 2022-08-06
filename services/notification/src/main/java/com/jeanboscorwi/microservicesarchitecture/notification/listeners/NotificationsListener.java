package com.jeanboscorwi.microservicesarchitecture.notification.listeners;

import com.jeanboscorwi.microservicesarchitecture.notification.models.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationsListener {

    public void handleMessage(NotificationRequest notificationRequest) {
        log.info("received the notification request: {}", notificationRequest.getPayload());
    }
}
