package com.example.asswrkspt.messaging.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Value("${wrkspot.customer.kafka.topic}")
    private String customerTopic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessageToCustomerTopic(String message) {
        kafkaTemplate.send(customerTopic, message);
    }
}
