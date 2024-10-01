package com.mq.cn.messagequeue.entity;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageQueue {

    private Map<String, Queue> queueList = new HashMap<>();

    public MessageQueue() {
        // 默认队列，用来实现全广播
        queueList.put("default", new Queue());
    }

    public synchronized void produce(String topic, Message message) throws InterruptedException {
        if (!queueList.containsKey(topic)) {
            queueList.put(topic, new Queue());
        }
        queueList.get(topic).put(message);
    }

    public synchronized Message consume(String topic) throws InterruptedException {
        if (!queueList.containsKey(topic)) {
            return null;
        }
        return queueList.get(topic).take();
    }


    public synchronized Queue getDefalutQueue() {
        return queueList.get("default");
    }

}
