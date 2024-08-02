package com.example.asswrkspt.messaging.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.asswrkspt.messaging.MessageService;
import com.example.asswrkspt.model.event.EventMessage;

@Service
public class KafkaMessageServiceImpl implements MessageService {

    @Autowired
    private KafkaProducerService kafkaProducer;

    @Override
    public <T> void sendMessage(EventMessage<T> message) {
        kafkaProducer.sendMessageToCustomerTopic(message.toJson());
    }

}
