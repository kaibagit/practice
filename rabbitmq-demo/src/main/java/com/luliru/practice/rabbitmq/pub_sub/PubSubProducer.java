package com.luliru.practice.rabbitmq.pub_sub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class PubSubProducer {

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("dev");
        factory.setPassword("123456");
        //设置 RabbitMQ 地址
        factory.setHost("192.168.0.107");
        Connection conn = factory.newConnection();

        Channel channel = conn.createChannel();
        //定义创建一个交换器 参数1 名称  参数2 交换器类型 参数3表示将交换器信息永久保存在服务器磁盘上 关闭rabbitmqserver也不会丢失
        //fanout发布订阅模式
        String EXCHANGE_NAME = "my_exchange";
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout",true);
        //同时发送10条消息
        for(int i=0;i<10;i++){
            String message="发送第"+i+"消息";
            //第二个参数就是routingkey  不填 默认会转发给所有的订阅者队列
            channel.basicPublish(EXCHANGE_NAME, "", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        }

        channel.close();
        conn.close();
    }
}
