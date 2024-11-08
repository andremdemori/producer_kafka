package com.live.kafka.producer.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDTO {

    private String uniqueID;
    private String subject;
    private String payload;
}
