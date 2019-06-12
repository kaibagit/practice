package me.luliru.practice.zookeeper.curator.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * InterProcessMutexTest
 * Created by luliru on 2019-06-12.
 */
public class InterProcessMutexTest {

    public static void main(String[] args) throws Exception {
        CuratorFramework client = getClient();
        InterProcessMutex mutex = new InterProcessMutex(client,"/demo/lock/mutex");
        InterProcessMutex mutex2 = new InterProcessMutex(client,"/demo/lock/mutex");
        mutex.acquire();
        System.out.println("mutex lock success");
        System.out.println("mutex.isAcquiredInThisProcess:"+mutex.isAcquiredInThisProcess());
        System.out.println("mutex2.isAcquiredInThisProcess:"+mutex2.isAcquiredInThisProcess());
        mutex2.acquire();
        System.out.println("mutex2 lock success");
        mutex.release();
    }

    private static CuratorFramework getClient() {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
        client.start();
        return client;
    }
}
