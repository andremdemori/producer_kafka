package com.live.kafka.producer.service;

import com.live.kafka.producer.ProducerApplication;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    public void restartSpringContext() {
        ProducerApplication.restart();
    }
}