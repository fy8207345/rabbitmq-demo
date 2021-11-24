package com.fy;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Publisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads(){
        rabbitTemplate.convertAndSend("boot-topic-exchange", "fast.red.rabbit", "fast.red.rabbit");
    }
}
