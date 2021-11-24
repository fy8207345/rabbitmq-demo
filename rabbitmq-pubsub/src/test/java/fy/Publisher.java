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
        String exchange = "pubsub-exchange";
        //fanout忽略routing key，给所有的绑定都发
        channel.exchangeDeclare(exchange, BuiltinExchangeType.FANOUT);
        channel.queueBind("pubsub-queue1", exchange, "");
        channel.queueBind("pubsub-queue2", exchange, "");

        for (int i = 0; i < 10; i++) {
            String msg = "PubSub:" + i;
            channel.basicPublish(exchange, "", null, msg.getBytes(StandardCharsets.UTF_8));
        }
        //释放资源
        channel.close();
        connection.close();
    }
}
