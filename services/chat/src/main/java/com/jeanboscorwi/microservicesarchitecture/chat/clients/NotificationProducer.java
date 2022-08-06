package com.jeanboscorwi.microservicesarchitecture.chat.clients;

import com.jeanboscorwi.microservicesarchitecture.chat.models.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationProducer {
    @Autowired
    private RedisTemplate<String, NotificationRequest> redisTemplate;


    private String messageTopic;

    public NotificationProducer(@Value("${spring.redis.broker.topic:notif-topic}") String messageTopic, @Qualifier("redisBrokerTemplate") RedisTemplate<String, NotificationRequest> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.messageTopic = messageTopic;
    }

    public void sendMessage(NotificationRequest notification) {
        redisTemplate.convertAndSend(messageTopic, notification);
        log.info("Message : {} is going to be sent to {}", notification.getPayload(), notification.getDeviceId());
    }
}
