package com.mq.cn.messagequeue.entity;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Queue {


    private final BlockingQueue<Message> queue = new ArrayBlockingQueue<>(1000);

    public void put(Message message) throws InterruptedException {
        queue.put(message);
    }

    public Message take() throws InterruptedException {
        return queue.take();
    }
}
