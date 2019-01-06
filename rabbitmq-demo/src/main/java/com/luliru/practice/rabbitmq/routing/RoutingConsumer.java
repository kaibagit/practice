package com.luliru.practice.rabbitmq.routing;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RoutingConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("dev");
        factory.setPassword("123456");
        factory.setHost("192.168.0.107");
        Connection conn = factory.newConnection();

        Channel channel = conn.createChannel();
        //消费者也需要定义队列 有可能消费者先于生产者启动
        String EXCHANGE_NAME = "my_log";
        channel.exchangeDeclare(EXCHANGE_NAME, "direct",true);
        //  channel.basicQos(1);
        //产生一个随机的队列 该队列用于从交换器获取消息
        String queueName = channel.queueDeclare().getQueue();
        //将队列和某个交换器绑定 设置key就可以正式获取消息了
        channel.queueBind(queueName, EXCHANGE_NAME, "info");
        System.out.println(" [*] Waiting for messages. To exit press CTRL+V");
        //定义回调抓取消息
        Consumer consumer = new DefaultConsumer(channel) {
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body);
                System.out.println(" [x] Received '" + message + "'");
                //参数2 true表示确认该队列所有消息  false只确认当前消息 每个消息都有一个消息标记

            }
        };
        //参数2 表示手动确认
        channel.basicConsume(queueName, true, consumer);
    }
}
