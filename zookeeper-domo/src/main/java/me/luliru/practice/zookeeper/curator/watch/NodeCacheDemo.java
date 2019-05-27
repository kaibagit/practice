package me.luliru.practice.zookeeper.curator.watch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class NodeCacheDemo {

    public static void main(String[] args) throws Exception {
        CuratorFramework client = getClient();
        String path = "/p1";
        final NodeCache nodeCache = new NodeCache(client, path);
        nodeCache.start();
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("监听事件触发");
                System.out.println("重新获得节点内容为：" + new String(nodeCache.getCurrentData().getData()));
            }
        });
        client.setData().forPath(path, "456".getBytes());
        Thread.sleep(1000);
        client.setData().forPath(path, "789".getBytes());
        Thread.sleep(1000);
        client.setData().forPath(path, "123".getBytes());
        Thread.sleep(1000);
        client.setData().forPath(path, "222".getBytes());
        Thread.sleep(1000);
        client.setData().forPath(path, "333".getBytes());
        Thread.sleep(1000);
        client.setData().forPath(path, "444".getBytes());
        Thread.sleep(15000);
    }

    private static CuratorFramework getClient() {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
        client.start();
        return client;
    }
}
