package com.luliru.practice.rabbitmq.work_queues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class WorkQueuesProducer {

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
        String QUEUE_NAME="work_queues";
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //发送消息
        for(int i=0;i<10;i++){
            channel.basicPublish("", QUEUE_NAME, null, ("这是："+i).getBytes()); //注意发送和接受段相同字符集否则出现乱码
        }

        channel.close();
        conn.close();
    }
}
