package com.jeanboscorwi.microservicesarchitecture.notification.configuration.redis.broker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeanboscorwi.microservicesarchitecture.notification.listeners.NotificationsListener;
import com.jeanboscorwi.microservicesarchitecture.notification.models.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
@ConditionalOnProperty(
        value="redis.enabled",
        havingValue = "true",
        matchIfMissing = true)
public class RedisConsumerConfig {

    @Value("${spring.redis.broker.topic:notif-topic}")
    private String notifTopic;

    @Bean
    public RedisMessageListenerContainer listenerContainer(MessageListenerAdapter listenerAdapter,
                                                           RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic(notifTopic));
        return container;
    }
    @Bean
    public MessageListenerAdapter listenerAdapter(NotificationsListener consumer) {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(consumer);
        messageListenerAdapter.setSerializer(new Jackson2JsonRedisSerializer<>(String.class));
        return messageListenerAdapter;
    }
    @Bean
    RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory,
                                                             Jackson2JsonRedisSerializer<String> serializer) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setDefaultSerializer(serializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
    
    @Bean
    public Jackson2JsonRedisSerializer<String> jackson2JsonRedisSerializer() {
        return new Jackson2JsonRedisSerializer<>(String.class);
    }
}
