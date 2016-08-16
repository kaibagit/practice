package com.luliru.test.zk;

import org.apache.zookeeper.*;

/**
 * Created by luliru on 2016/8/16.
 */
public class DefaultWatcherTest {

    public static void main(String[] args) throws Exception {
        // 创建一个与服务器的连接
        ZooKeeper zk = new ZooKeeper("localhost:" + 2181,
                50000, new Watcher() {
            // 监控所有被触发的事件
            public void process(WatchedEvent event) {
                System.out.println("已经触发了" + event.getType() + "事件！");
            }
        });

//        zk.getData("/default",true,null);
        zk.exists("/default2",true);

        while(true){
            Thread.sleep(500L);
        }
    }
}
