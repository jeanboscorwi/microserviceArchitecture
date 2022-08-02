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
public class MessageRequest implements Serializable {
    private Long sourceId;
    private Long recipientId;
    private String message;
}
