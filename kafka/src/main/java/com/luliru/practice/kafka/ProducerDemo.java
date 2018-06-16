package com.luliru.practice.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by luliru on 2018/6/16.
 */
public class ProducerDemo {

    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers","192.168.0.106:9091,192.168.0.106:9092,192.168.0.106:9093");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer producer = new KafkaProducer<String, String>(props);
        int i = 0;
        while(true){
            i++;
            producer.send(new ProducerRecord<String, String>("mytopic2", Integer.toString(i), Integer.toString(i)));
            System.out.println("produce:"+i);
            Thread.sleep(1000L);
        }
    }
}
