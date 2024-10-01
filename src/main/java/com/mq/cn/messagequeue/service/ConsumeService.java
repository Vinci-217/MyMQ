package com.mq.cn.messagequeue.service;

import com.mq.cn.messagequeue.entity.CustomEvent;
import com.mq.cn.messagequeue.entity.Message;
import com.mq.cn.messagequeue.entity.MessageQueue;
import com.mq.cn.messagequeue.entity.Queue;
import com.mq.cn.messagequeue.handler.BroadcastHandler;
import com.mq.cn.messagequeue.handler.PointToPointHandler;
import com.mq.cn.messagequeue.handler.PublishSubscribeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ConsumeService {

    @Autowired
    private BroadcastHandler broadcastHandler;

    @Autowired
    private PointToPointHandler pointToPointHandler;

    @Autowired
    private PublishSubscribeHandler publishSubscribeHandler;

    @Autowired
    private MessageQueue messageQueue;

    @EventListener(condition = "#event.message == 'broadcast'")
    public void broadcast(CustomEvent event) throws InterruptedException {
        Queue defalutQueue = messageQueue.getDefalutQueue();
        Message message = defalutQueue.take();
        String topic = event.getTopic();
        broadcastHandler.handle(message,topic);
    }

    @EventListener(condition = "#event.message == 'point-to-point'")
    public void pointToPoint(CustomEvent event) throws InterruptedException {
        // 从指定的主题队列中获取消息并处理
        String topic = event.getTopic(); // 假设事件中包含主题信息
        Message message = messageQueue.consume(topic);
        if (message != null) {
            pointToPointHandler.handle(message,topic);
        }
    }


    @EventListener(condition = "#event.message == 'publish-subscribe'")
    public void publishSubscribe(CustomEvent event) throws InterruptedException {
        String topic = event.getTopic(); // 获取主题
        Message message = messageQueue.consume(topic);
        if (message != null) {
            publishSubscribeHandler.handle(message,topic);
        }
    }



}
