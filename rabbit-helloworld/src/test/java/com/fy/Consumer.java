package com.fy;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    @Test
    public void comsume() throws IOException, TimeoutException {
        //获取链接
        Connection connection = RabbitMQClient.getConnection();
        //创建channel
        Channel channel = connection.createChannel();
        //声明队列
        /*
            1. 队列名称
            2. 持久性
            3. 排外性
            4. 自动删除
            5. 队列的属性
         */
        channel.queueDeclare("HelloWorld", true, false, false, null);

        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("接收到消息：" + new String(body));
            }
        };

        channel.basicConsume("HelloWorld", true, consumer);

        //防止程序结束
        System.in.read();
        channel.close();
        connection.close();
    }
}
