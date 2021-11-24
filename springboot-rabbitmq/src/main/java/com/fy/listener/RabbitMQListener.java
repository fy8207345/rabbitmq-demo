package com.fy.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.PublisherCallbackChannel;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class RabbitMQListener {

    @RabbitListener(queues = "boot-queue")
    public void onMessage(String msg, Channel channel, Message message, @Header(value = "foo", required = false) String foo) throws IOException {
        log.info("接收到消息string：{} - {}" , msg, message.getMessageProperties());
        //手动ack
        System.out.println(foo);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
