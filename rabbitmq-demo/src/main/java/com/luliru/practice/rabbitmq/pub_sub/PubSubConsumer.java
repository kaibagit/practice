package com.luliru.practice.rabbitmq.pub_sub;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class PubSubConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("dev");
        factory.setPassword("123456");
        factory.setHost("192.168.0.107");
        Connection conn = factory.newConnection();

        Channel channel = conn.createChannel();
        //消费者也需要定义队列 有可能消费者先于生产者启动
        String EXCHANGE_NAME = "my_exchange";
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout",true);
        //  channel.basicQos(1);
        //产生一个随机的队列 该队列用于从交换器获取消息
        String queueName = channel.queueDeclare().getQueue();
        //将队列和某个交换器绑定 就可以正式获取消息了 routingkey和交换器的一样都设置成空
        channel.queueBind(queueName, EXCHANGE_NAME, "");
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
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
