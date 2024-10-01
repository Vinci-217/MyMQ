package com.mq.cn.messagequeue.service;

import com.mq.cn.messagequeue.entity.CustomEvent;
import com.mq.cn.messagequeue.entity.Message;
import com.mq.cn.messagequeue.entity.MessageQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ProduceService {

    @Autowired
    private MessageQueue messageQueue;

    @Autowired
    private ApplicationContext applicationContext;

    public void broadcast(Message message) throws InterruptedException {
        messageQueue.getDefalutQueue().put(message);
        CustomEvent customEvent = new CustomEvent(this, "broadcast");
        applicationContext.publishEvent(customEvent);
    }

    public void pointToPoint(Message message, String topic) throws InterruptedException {
        messageQueue.produce(topic, message); // 将消息发送到特定主题的队列
        CustomEvent customEvent = new CustomEvent(this,"point-to-point",topic);
        applicationContext.publishEvent(customEvent);
    }


    public void publishSubscribe(Message message, String topic) throws InterruptedException {
        messageQueue.produce(topic, message); // 将消息发送到特定主题的队列
        CustomEvent customEvent = new CustomEvent(this,"publish-subscribe",topic);
        applicationContext.publishEvent(customEvent);
    }

}
