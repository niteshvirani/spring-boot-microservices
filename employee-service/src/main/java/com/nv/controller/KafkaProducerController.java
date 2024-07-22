package com.nv.controller;

import com.nv.producer.KafkaMessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/kafka-producers")
@RequiredArgsConstructor
public class KafkaProducerController {

    private final KafkaMessageProducer kafkaMessageProducer;

    @PostMapping("/publish/message")
    public ResponseEntity<?> publishMessage(@RequestParam String message) {
        try {
            kafkaMessageProducer.publishMessage(message);
            return ResponseEntity.ok("Message published successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/publish/bulk-message")
    public ResponseEntity<?> publishBulkMessage(@RequestParam int count, @RequestParam String message) {
        try {
            kafkaMessageProducer.publishBulkMessages(count, message);
            return ResponseEntity.ok("Message published successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
