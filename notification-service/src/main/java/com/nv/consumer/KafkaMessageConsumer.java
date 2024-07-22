package com.nv.consumer;

import com.nv.dto.EmployeeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageConsumer {

    @KafkaListener(topics = "employee-created", groupId = "employee-group-1")
    public void consumeEmployeeCreated(EmployeeDto message) {
        log.info("Consumer (employee-created) consumed the message: {}", message);
    }

    @KafkaListener(topics = "kafka-topic-1", groupId = "kafka-group-1")
    public void consumeKafkaTopic1(String message) {
        log.info("consumeKafkaTopic1 consumed the message: {}", message);
    }

    // kafka-topic-2 has 3 partitions, but we defined 4 consumers for kafka-topic-2
    // one extra consumer for backup
    @KafkaListener(topics = "kafka-topic-2", groupId = "kafka-group-2")
    public void consumeKafkaTopic2_0(String message) {
        log.info("consumeKafkaTopic2_0 consumed the message: {}", message);
    }

    @KafkaListener(topics = "kafka-topic-2", groupId = "kafka-group-2")
    public void consumeKafkaTopic2_1(String message) {
        log.info("consumeKafkaTopic2_1 consumed the message: {}", message);
    }

    @KafkaListener(topics = "kafka-topic-2", groupId = "kafka-group-2")
    public void consumeKafkaTopic2_2(String message) {
        log.info("consumeKafkaTopic2_2 consumed the message: {}", message);
    }

    @KafkaListener(topics = "kafka-topic-2", groupId = "kafka-group-2")
    public void consumeKafkaTopic2_3(String message) {
        log.info("consumeKafkaTopic2_3 consumed the message: {}", message);
    }
}
