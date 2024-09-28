package com.mq.cn.messagequeue.entity;


import org.springframework.stereotype.Component;

import java.util.Deque;
import java.util.LinkedList;

@Component
public class MessageQueue {
    private final Deque<Message> queue = new LinkedList<>();

    public synchronized void publish(Message message) {
        queue.offer(message);
        notifyAll(); // 通知等待的消费者
    }

    public synchronized Message consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // 等待消息
        }
        return queue.poll();
    }
}
