package com.mq.cn.messagequeue.controller;

import com.mq.cn.messagequeue.entity.Message;
import com.mq.cn.messagequeue.entity.Result;
import com.mq.cn.messagequeue.service.ProduceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MessageController {


    @Autowired
    private ProduceService produceService;

    @PostMapping("/broadcast")
    public Result broadcast(@RequestBody Message message) throws InterruptedException {
//        log.info(String.valueOf(message));
        produceService.broadcast(message) ;
        return Result.success();
    }

    @PostMapping("/point-to-point/{topic}")
    public Result pointToPoint(@RequestBody Message message, @PathVariable("topic") String topic) throws InterruptedException {
        produceService.pointToPoint(message, topic);
        return Result.success();
    }

    @PostMapping("/publish-subscribe/{topic}")
    public Result publishSubscribe(@RequestBody Message message, @PathVariable("topic") String topic) throws InterruptedException {
        produceService.publishSubscribe(message, topic);
        return Result.success();
    }






}