package com.mq.cn.messagequeue.handler;


import com.mq.cn.messagequeue.entity.Message;

public interface MessageHandler {
    void handle(Message message,String topic);
}