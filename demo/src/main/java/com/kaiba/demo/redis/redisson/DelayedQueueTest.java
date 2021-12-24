package com.kaiba.demo.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * DelayedQueueTest
 * Created by luliru on 12/24/21.
 */
public class DelayedQueueTest {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("localhost:6379");
        RedissonClient redisson = Redisson.create(config);

        RBlockingQueue<String> blockingQueue = redisson.getBlockingQueue("dest_queue1");
        RDelayedQueue<String> delayedQueue = redisson.getDelayedQueue(blockingQueue);

        new Thread() {
            public void run() {
                while(true) {
                    try {
                        //阻塞队列有数据就返回，否则wait
                        System.err.println( blockingQueue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        }.start();

        for(int i=1;i<=5;i++) {
            // 向阻塞队列放入数据
            delayedQueue.offer("hello:"+i, 13, TimeUnit.SECONDS);
        }
    }
}
