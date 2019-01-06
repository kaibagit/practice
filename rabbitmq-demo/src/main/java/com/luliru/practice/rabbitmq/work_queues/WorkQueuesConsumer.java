package com.luliru.practice.rabbitmq.work_queues;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class WorkQueuesConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("dev");
        factory.setPassword("123456");
        factory.setHost("192.168.0.107");
        Connection conn = factory.newConnection();

        Channel channel = conn.createChannel();
        String QUEUE_NAME="work_queues";
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        Consumer consumer = new DefaultConsumer(channel) {
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println(msg);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
