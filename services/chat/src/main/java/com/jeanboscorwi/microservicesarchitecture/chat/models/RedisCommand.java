package com.jeanboscorwi.microservicesarchitecture.chat.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RedisCommand<T> implements Serializable {
    private String xB3TraceId;
    private T payload;
}
