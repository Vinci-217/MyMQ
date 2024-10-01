package com.mq.cn.messagequeue.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consumer {

    private String id;
    private String topic;

    public Consumer(String id) {
        this.id = id;
    }

    public void receiveMessage(String message) {
        System.out.println("Consumer " + id + " received message: " + message);
    }

}
