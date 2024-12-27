package com.example.producerconsumersimulation.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SendUpdateService {
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public SendUpdateService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Sends an update message to the specified topic.
     *
     * @param message the message to send.
     */
    public void sendUpdate(String message) {
        messagingTemplate.convertAndSend("/topic/updates", message);
    }

    /**
     * Sends a complex object update to the specified topic.
     *
     * @param topic the topic to send the update to.
     * @param payload the object to send.
     */
    public void sendUpdate(String topic, Object payload) {
        messagingTemplate.convertAndSend(topic, payload);
    }
}
