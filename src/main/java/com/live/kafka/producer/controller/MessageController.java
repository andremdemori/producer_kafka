package com.live.kafka.producer.controller;

import com.live.kafka.producer.DTO.MessageDTO;
import com.live.kafka.producer.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageProducer messageProducer;

    @PostMapping
    public ResponseEntity<MessageDTO> create(@RequestBody MessageDTO messageDTO){
        MessageDTO message = MessageDTO.builder()
                .uniqueID(UUID.randomUUID().toString())
                .subject(messageDTO.getSubject())
                .payload(messageDTO.getPayload())
                .build();
        messageProducer.send(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
}
