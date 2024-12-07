package br.mil.defesa.interc2.producer.controller;

import br.mil.defesa.interc2.producer.DTO.TgiMessage;
import br.mil.defesa.interc2.producer.producer.MessageProducer;
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
    public ResponseEntity<TgiMessage> create(@RequestBody TgiMessage tgimessage){
        // Usando o construtor padrão do record
        TgiMessage message = new TgiMessage(
                UUID.randomUUID().toString(),
                tgimessage.subject(),
                tgimessage.payload()
        );

        messageProducer.send(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
}
