package com.fy.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RabbitListener(queues = "boot-queue")
public class RabbitMQListener {

    @RabbitHandler
    public void onMessage(byte[] message){
        log.info("接收到消息bytes：{}" , new String(message));
    }

    @RabbitHandler
    public void onMessage(String msg, Channel channel, Message message) throws IOException {
        log.info("接收到消息string：{}" , msg);
        //手动ack
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
