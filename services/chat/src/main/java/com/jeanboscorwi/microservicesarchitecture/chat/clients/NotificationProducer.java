package com.jeanboscorwi.microservicesarchitecture.chat.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeanboscorwi.microservicesarchitecture.chat.models.NotificationRequest;
import com.jeanboscorwi.microservicesarchitecture.chat.models.RedisCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationProducer {
    
    @Autowired
    private final Tracer tracer;
    
    private RedisTemplate<String, String> redisTemplate;
    private ObjectMapper objectMapper;
    private String messageTopic;

    public NotificationProducer(ObjectMapper objectMapper, Tracer tracer, @Value("${spring.redis.broker.topic:notif-topic}") String messageTopic, @Qualifier("redisBrokerTemplate") RedisTemplate<String, String> redisTemplate) {
        this.tracer = tracer;
        this.redisTemplate = redisTemplate;
        this.messageTopic = messageTopic;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(NotificationRequest notification) throws JsonProcessingException {
        var commandToSend = buildRedisCommand(notification);
        redisTemplate.convertAndSend(messageTopic,  commandToSend);
        log.info("Message : {} is going to be sent to {}", notification.getPayload(), notification.getDeviceId());
    }
    
    private String buildRedisCommand(NotificationRequest notification) throws JsonProcessingException {
        Span currentSpan = this.tracer.currentSpan();
        String traceId = null;
        if(currentSpan != null){
            traceId = currentSpan.context().spanId();
        }
        
        var redisCommand =  new RedisCommand<NotificationRequest>(traceId, notification);
        return objectMapper.writeValueAsString(redisCommand);
    }
}
