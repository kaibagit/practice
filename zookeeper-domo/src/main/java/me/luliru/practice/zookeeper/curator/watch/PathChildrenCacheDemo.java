package me.luliru.practice.zookeeper.curator.watch;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.nio.charset.Charset;
import java.util.List;

public class PathChildrenCacheDemo {

    public static final Charset CHARSET = Charset.forName("UTF-8");

    private static CuratorFramework getClient() {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
        client.start();
        return client;
    }

    public static void main(String[] args) throws Exception {
        CuratorFramework client = getClient();

        final PathChildrenCache pathChildrenCache = new PathChildrenCache(client, "/pandora", true);
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework,
                                   PathChildrenCacheEvent event) throws Exception {

                System.out.println("======== catch children change =======");
                System.out.println("update event type:" + event.getType() +",data:" + event.getData());

                List<ChildData> childDataList = pathChildrenCache.getCurrentData();
                if (childDataList != null && childDataList.size() > 0) {
                    System.out.println("path all children list:");
                    for (ChildData childData : childDataList) {
                        System.out.println("path:" + childData.getPath() + "," + event.getData());
                    }
                }
            }
        });
        pathChildrenCache.start();  //must call start();

        Thread.sleep(60000L);

        pathChildrenCache.close();
    }
}
