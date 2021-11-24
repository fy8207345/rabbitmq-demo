package fy;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Consumer1 {

    @Test
    public void comsume() throws IOException, TimeoutException {
        //获取链接
        Connection connection = RabbitMQClient.getConnection();
        //创建channel
        Channel channel = connection.createChannel();
        channel.queueDeclare("WorkQueue", true, false, false, null);

        //一次能消费多少条消息
        channel.basicQos(1);

        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //模拟处理数据
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("接收到消息：" + new String(body));

                //手动ack
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        //取消自动ack
        channel.basicConsume("WorkQueue", false, consumer);
        //防止程序结束
        System.in.read();
        channel.close();
        connection.close();
    }
}
