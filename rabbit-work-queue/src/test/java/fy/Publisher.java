package fy;

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

        for (int i = 0; i < 10; i++) {
            String msg = "WorkQueue:" + i;
            channel.basicPublish("", "WorkQueue", null, msg.getBytes(StandardCharsets.UTF_8));
        }
        //释放资源
        channel.close();
        connection.close();
    }
}
