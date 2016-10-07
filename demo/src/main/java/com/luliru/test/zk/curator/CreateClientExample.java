package com.luliru.test.zk.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

/**
 * Created by luliru on 2016/9/19.
 */
public class CreateClientExample {

    public static void main(String[] args) throws Exception {
        String connectionString = "localhost:2181";

        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);

        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionString, retryPolicy);

        client = CuratorFrameworkFactory.builder().connectString(connectionString)
                .retryPolicy(retryPolicy)
                .canBeReadOnly(false)
                .connectionTimeoutMs(30000)
                .sessionTimeoutMs(30000)
                // etc. etc.
                .build();

        client.start();
        CloseableUtils.closeQuietly(client);
    }
}
