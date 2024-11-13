package com.live.kafka.producer.controller;

import com.live.kafka.producer.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    ConfigService configService;

    @CrossOrigin("*")
    @PostMapping("/restart")
    public void restart(){
        configService.restartSpringContext();
    }
}
