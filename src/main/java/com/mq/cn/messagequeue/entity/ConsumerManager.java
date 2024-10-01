package com.mq.cn.messagequeue.entity;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ConsumerManager {
    private Map<String, Consumer> consumers = new HashMap<>();

    // 测试注入默认消费者
    public ConsumerManager() {
        registerConsumer(new Consumer("1", "default"));
        registerConsumer(new Consumer("2", "default"));
        registerConsumer(new Consumer("3", "default"));
        registerConsumer(new Consumer("4", "1"));
        registerConsumer(new Consumer("5", "2"));
        registerConsumer(new Consumer("6", "3"));
    }

    public void registerConsumer(Consumer consumer) {
        consumers.put(consumer.getId(), consumer);
    }

    public Consumer getConsumer(String id) {
        return consumers.get(id);
    }

    public List<Consumer> getConsumersByTopic(String topic) {
        List<Consumer> ans = new ArrayList<>();
        for (Consumer consumer : consumers.values()) {
            if (consumer.getTopic().equals(topic)) {
                ans.add(consumer);
            }
        }
        return ans;
    }

    public Map<String, Consumer> getAllConsumers() {
        return consumers;
    }
}
