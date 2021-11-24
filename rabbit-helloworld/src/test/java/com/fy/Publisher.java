package com.fy;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Publisher {

    @Test
    public void publish() throws IOException, TimeoutException {
        //获取链接
        Connection connection = RabbitMQClient.getConnection();
        //创建channel
        Channel channel = connection.createChannel();
        /*
         * 发布消息到指定exchange,同时指定路由规则
         * 1. exchange
         * 2. routingkey
         * 3. 消息的属性
         * 4. 消息的数据：byte[]
         */
        channel.basicPublish("", "HelloWorld", null, "Hello".getBytes(StandardCharsets.UTF_8));
        //释放资源
        channel.close();
        connection.close();
    }
}
