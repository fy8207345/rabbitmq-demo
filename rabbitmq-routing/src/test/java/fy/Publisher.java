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
        String exchange = "routing-exchange";
        //routing必须为direct类型
        channel.exchangeDeclare(exchange, BuiltinExchangeType.DIRECT);
        channel.queueBind("routing-queue-error", exchange, "ERROR");
        channel.queueBind("routing-queue-info", exchange, "INFO");

        for (int i = 0; i < 10; i++) {
            String msg = "ERROR:" + i;
            channel.basicPublish(exchange, "ERROR", null, msg.getBytes(StandardCharsets.UTF_8));
        }

        for (int i = 0; i < 10; i++) {
            String msg = "INFO:" + i;
            channel.basicPublish(exchange, "INFO", null, msg.getBytes(StandardCharsets.UTF_8));
        }
        //释放资源
        channel.close();
        connection.close();
    }
}
