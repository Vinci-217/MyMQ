package com.mq.cn.messagequeue.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Subscriber  {

    @Autowired
    private MessageQueue messageQueue;

    public Message subscribe() throws InterruptedException {
        return messageQueue.consume();
    }

}
