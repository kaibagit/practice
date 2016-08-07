package com.kaiba.demo.rocketmq;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * Created by kaiba on 2016/8/6.
 */
public class Producer {
    public static void main(String[] args){
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
        producer.setNamesrvAddr("localhost:9876");
        //producer.setNamesrvAddr("192.168.36.189:9876;192.168.36.54:9876");
        producer.setInstanceName("Producer_1");
        try {
            producer.start();

            Message msg = new Message("topic5","tags","keys3","This is a Message.".getBytes());

            for(int i =0;i< 16;i++){
                SendResult result = producer.send(msg);
                System.out.println("id:" + result.getMsgId() + " result:" + result.getSendStatus());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            producer.shutdown();
        }
    }
}
