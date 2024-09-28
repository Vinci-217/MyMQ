package com.mq.cn.messagequeue;

import com.mq.cn.messagequeue.entity.MessageQueue;
import com.mq.cn.messagequeue.entity.Publisher;
import com.mq.cn.messagequeue.entity.Subscriber;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

@SpringBootTest
class MessageQueueApplicationTests {

    @Autowired
    private Publisher publisher;
    @Autowired
    private Subscriber subscriber;

    @Test
    void contextLoads() {

    }

    @Test
    void test() {

        ExecutorService publisherExecutor =
                new ThreadPoolExecutor(10, 100,
                        0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>());


        ExecutorService subscriberExecutor = new ThreadPoolExecutor(10, 100,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());



        for (int i = 0; i < 50; i++) {
            int finalI = i;
            publisherExecutor.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Publishing message " + finalI);
                publisher.sendMessage("Message " + finalI);
            });
        }


        for (int i = 0; i < 50; i++) {
            subscriberExecutor.submit(() -> {
                try {
                    System.out.println(subscriber.subscribe().getContent());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        // 关闭发布者线程池
        publisherExecutor.shutdown();


        // 关闭订阅者线程池
        subscriberExecutor.shutdown();

        try {
            // 等待所有发布者任务完成
            if (!publisherExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                publisherExecutor.shutdownNow();
            }
            // 等待所有订阅者任务完成
            if (!subscriberExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                subscriberExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            publisherExecutor.shutdownNow();
            subscriberExecutor.shutdownNow();
        }



    }

}
