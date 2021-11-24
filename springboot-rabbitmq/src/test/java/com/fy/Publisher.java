package com.fy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
@SpringBootTest
public class Publisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() throws IOException {
        String id = UUID.randomUUID().toString();
        log.info("发送消息的id: {}", id);
//        CorrelationData messageId = new CorrelationData(id);
//        rabbitTemplate.convertAndSend("boot-topic-exchange", "fast.red.rabbit", "fast.black.rabbit", messageId);
//        MessageProperties properties = MessagePropertiesBuilder.newInstance()
//                .setMessageId(id)
//                .setHeader("foo", "bar")
//                .build();
//        Message message = MessageBuilder.withBody("fast.red.rabbit".getBytes(StandardCharsets.UTF_8))
//                .andProperties(properties)
//                .build();
        //发送meSsage对象，可以携带更多属性
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setMessageId(id);
        messageProperties.setHeader("foo", "bar");
        Message message = new Message("fast.red.rabbit".getBytes(StandardCharsets.UTF_8), messageProperties);
        rabbitTemplate.convertAndSend("boot-topic-exchange", "fast.red.rabbit", message);
    }
}
