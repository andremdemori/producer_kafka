package com.live.kafka.producer.producer;

import com.live.kafka.producer.config.KafkaConfig;
import com.live.kafka.producer.controller.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);
    private final KafkaTemplate<String, MessageDTO> kafkaTemplate;
    private final KafkaConfig kafkaConfig;

    public MessageProducer(KafkaTemplate<String, MessageDTO> kafkaTemplate, KafkaConfig kafkaConfig) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaConfig = kafkaConfig;
    }

    //método para enviar mensagem
    public void send(MessageDTO messageDTO){
        kafkaTemplate.send(kafkaConfig.getTopicName(), messageDTO).addCallback(
                success -> logger.info("Message sent" + success.getProducerRecord().value()),
                failure -> logger.info("Message failure" + failure.getMessage())
        );
    }
}
