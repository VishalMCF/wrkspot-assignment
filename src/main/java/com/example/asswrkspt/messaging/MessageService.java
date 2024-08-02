package com.example.asswrkspt.messaging;

import com.example.asswrkspt.model.event.EventMessage;

public interface MessageService {

    public <T> void sendMessage(EventMessage<T> message);

}
