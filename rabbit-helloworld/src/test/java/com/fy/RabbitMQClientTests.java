package com.fy;

import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.IOException;

public class RabbitMQClientTests {

    @Test
    public void test() throws IOException {
        Connection connection = RabbitMQClient.getConnection();
        connection.close();
    }
}
