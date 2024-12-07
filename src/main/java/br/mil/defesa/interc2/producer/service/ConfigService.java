package br.mil.defesa.interc2.producer.service;

import br.mil.defesa.interc2.producer.ProducerApplication;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    public void restartSpringContext() {
        ProducerApplication.restart();
    }
}