package fy;

import com.rabbitmq.client.BuiltinExchangeType;
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
        //创建exchange
        String exchange = "topic-exchange";
        //必须为TOPIC类型
        channel.exchangeDeclare(exchange, BuiltinExchangeType.TOPIC);
        channel.queueBind("topic-queue-1", exchange, "*.red.*");
        channel.queueBind("topic-queue-2", exchange, "fast.#");
        channel.queueBind("topic-queue-2", exchange, "*.*.rabbit");

        channel.basicPublish(exchange, "fast.red.rabbit", null, "fast.red.rabbit".getBytes(StandardCharsets.UTF_8));
        channel.basicPublish(exchange, "slow.red.cat", null, "slow.red.cat".getBytes(StandardCharsets.UTF_8));
        channel.basicPublish(exchange, "slow.white.cat", null, "slow.white.cat".getBytes(StandardCharsets.UTF_8));
        //释放资源
        channel.close();
        connection.close();
    }
}
