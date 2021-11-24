package com.fy.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 一定要注意：confirm和return机制的回调都是告诉发布者，而不是消费者。消费者收不到这两类消息
 */
@Slf4j
@Component
public class PublisherConfirmAndReturnCallback implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
            log.info("消息已经到达exchange");
        }else{
            log.info("消息没有到达exchange");
        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.info("消息没有到达queue");
    }
}
