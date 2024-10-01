package com.mq.cn.messagequeue.handler;

import com.mq.cn.messagequeue.entity.Consumer;
import com.mq.cn.messagequeue.entity.Message;
import com.mq.cn.messagequeue.entity.ConsumerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BroadcastHandler implements MessageHandler {


    @Autowired
    private ConsumerManager consumerManager;

    @Override
    public void handle(Message message, String topic) {
        // 遍历所有消费者并推送消息
        for (Consumer consumer : consumerManager.getAllConsumers().values()) {
            consumer.receiveMessage(message.getContent());
        }
    }
}