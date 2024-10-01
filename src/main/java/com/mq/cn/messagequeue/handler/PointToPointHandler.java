package com.mq.cn.messagequeue.handler;

import com.mq.cn.messagequeue.entity.Consumer;
import com.mq.cn.messagequeue.entity.Message;
import com.mq.cn.messagequeue.entity.ConsumerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PointToPointHandler implements MessageHandler {

    @Autowired
    private ConsumerManager consumerManager;

    @Override
    public void handle(Message message,String topic) {
        List<Consumer> consumers = consumerManager.getConsumersByTopic(topic);
        consumers.get(0).receiveMessage(message.getContent());
    }
}