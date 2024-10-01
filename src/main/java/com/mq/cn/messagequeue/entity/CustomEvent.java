package com.mq.cn.messagequeue.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEvent;


@Getter
public class CustomEvent extends ApplicationEvent {
    private String message;
    // 添加获取主题的方法
    private String topic; // 添加主题属性

    public CustomEvent(Object source, String message, String topic) {
        super(source);
        this.message = message;
        this.topic = topic; // 初始化主题属性
    }

    public CustomEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

}
