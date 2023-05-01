package com.athi.eba.kafka.controller;

import com.athi.eba.kafka.service.KafkaConsumerService;
import com.athi.eba.kafka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam String topic, @RequestParam String message) {
        kafkaProducerService.sendMessage(topic, message);
        return ResponseEntity.ok("Message sent successfully");
    }

    @GetMapping("/receive")
    public ResponseEntity<String> receiveMessage(@RequestParam String topic) {
        kafkaConsumerService.receiveMessage(topic);
        return ResponseEntity.ok("Message received successfully");
    }
}