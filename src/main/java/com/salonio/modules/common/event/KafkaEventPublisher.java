package com.salonio.modules.common.event;

import org.springframework.stereotype.Service;

@Service
public class KafkaEventPublisher implements DomainEventPublisher {
//    private final KafkaTemplate<String, Object> kafkaTemplate;
//
//    public KafkaEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }

    @Override
    public void publish(Object event) {
//        kafkaTemplate.send("", event);
    }
}
