package com.nv.producer;

import com.nv.dto.EmployeeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaMessageProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaMessageProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishEmployeeCreated(EmployeeDto employee) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("employee-created", employee);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message: {} with offset: {}", employee.toString(), result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send message: {} due to: {}", employee.toString(), ex.getMessage());
            }
        });
    }

    public void publishMessage(String message) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("kafka-topic-1", message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message: {} with offset: {}", message, result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send message: {} due to: {}", message, ex.getMessage());
            }
        });
    }

    public void publishBulkMessages(int count, String message) {
        log.info("Sending {} messages: {}", count, message);
        for (int i = 0; i < count; i++) {
            kafkaTemplate.send("kafka-topic-2", message + " : " + i);
        }
    }
}
