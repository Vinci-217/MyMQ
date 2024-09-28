package com.mq.cn.messagequeue.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Publisher {
    @Autowired
    private MessageQueue messageQueue;

    public void sendMessage(String content) {
        Message message = new Message(content);
        messageQueue.publish(message);
    }
}
