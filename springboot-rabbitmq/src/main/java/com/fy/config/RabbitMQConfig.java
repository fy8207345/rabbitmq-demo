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
    public Binding binding(TopicExchange topicExchange, Queue queue){
        //需要注意：运行之后修改了routing key，会重新添加一个绑定，之前的绑定依然存在。
        return BindingBuilder.bind(queue).to(topicExchange).with("*.red.*");
    }
}
