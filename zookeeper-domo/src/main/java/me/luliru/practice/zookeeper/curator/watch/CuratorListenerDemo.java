package me.luliru.practice.zookeeper.curator.watch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorListenerDemo {

    public static void main(String[] args) {
        CuratorFramework client = getClient();
        String path = "/p1";
        try {
            CuratorListener listener = new CuratorListener() {
                @Override
                public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                    System.out.println("监听事件触发，event内容为：" + event);
                }
            };
            client.getCuratorListenable().addListener(listener); // 异步获取节点数据
            client.getData().inBackground().forPath(path); // 变更节点内容
            client.setData().forPath(path, "123".getBytes());
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
            client.close();
        } finally {
            client.close();
        }
    }

    private static CuratorFramework getClient() {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
        client.start();
        return client;
    }
}
