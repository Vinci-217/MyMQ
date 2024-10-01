package com.mq.cn.messagequeue;

import com.mq.cn.messagequeue.controller.MessageController;
import com.mq.cn.messagequeue.entity.Message;
import com.mq.cn.messagequeue.service.ProduceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ControllerTest {

    @Test
    public void testBroadcast() throws Exception {

        String URL = "http://localhost:15672/broadcast";
        int numberOfThreads = 10; // 设置并发请求的线程数
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        HttpClient client = HttpClient.newHttpClient();

        for (int i = 0; i < numberOfThreads; i++) {
            executor.submit(() -> {
                try {
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(URL))
                            .header("Content-Type", "application/json")
                            .POST(HttpRequest.BodyPublishers.ofString("{\"content\": \"消息内容\"}")) // 替换为实际的请求体
                            .build();

                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    System.out.println("Response: " + response.body());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
    }
}
