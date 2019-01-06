package com.luliru.practice.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SimpleProducer {

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("dev");
        factory.setPassword("123456");
        //设置 RabbitMQ 地址
        factory.setHost("192.168.0.107");
        Connection conn = factory.newConnection();

        Channel channel = conn.createChannel();
        //定义创建一个队列
        String QUEUE_NAME="simple_queue";
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //发送消息
        channel.basicPublish("", QUEUE_NAME, null, "SimpleProducer".getBytes());

        channel.close();
        conn.close();
    }
}
