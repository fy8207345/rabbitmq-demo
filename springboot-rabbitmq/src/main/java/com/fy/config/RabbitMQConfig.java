package com.fy.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //Exchange
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("boot-topic-exchange", true, false);
    }

    //队列
    @Bean
    public Queue queue(){
        return new Queue("boot-queue", true, false, false, null);
    }

    //绑定
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(topicExchange()).with("*.red.*");
    }
}
