package com.live.kafka.producer.producer;

import com.live.kafka.producer.DTO.TgiMessage;
import com.live.kafka.producer.config.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);
    private final KafkaTemplate<String, TgiMessage> kafkaTemplate;
    private final KafkaConfig kafkaConfig;

    public MessageProducer(KafkaTemplate<String, TgiMessage> kafkaTemplate, KafkaConfig kafkaConfig) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaConfig = kafkaConfig;
    }

    //método para enviar mensagem
    public void send(TgiMessage messageDTO){
        kafkaTemplate.send(kafkaConfig.getTopicName(), messageDTO).addCallback(
                success -> logger.info("Message sent" + success.getProducerRecord().value()),
                failure -> logger.info("Message failure" + failure.getMessage())
        );
    }
}
