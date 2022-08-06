package com.jeanboscorwi.microservicesarchitecture.chat.configuration.redis.messaging;

import com.jeanboscorwi.microservicesarchitecture.chat.models.NotificationRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisProducerConfig {
    @Bean("redisBrokerTemplate")
    RedisTemplate<String, NotificationRequest> redisTemplate(RedisConnectionFactory connectionFactory,
                                                             Jackson2JsonRedisSerializer<NotificationRequest> serializer) {

        RedisTemplate<String, NotificationRequest> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setDefaultSerializer(serializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public Jackson2JsonRedisSerializer<NotificationRequest> jackson2JsonRedisSerializer() {
        return new Jackson2JsonRedisSerializer<>(NotificationRequest.class);
    }
}
