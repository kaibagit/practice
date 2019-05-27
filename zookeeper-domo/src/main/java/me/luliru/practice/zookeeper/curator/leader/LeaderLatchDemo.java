package me.luliru.practice.zookeeper.curator.leader;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

/**
 * Created by luliru on 2016/9/29.
 */
public class LeaderLatchDemo {

    public static void main(String[] args) throws Exception {
        TestingServer server = new TestingServer();
        CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(),new ExponentialBackoffRetry(20000,3));
        client.start();

        LeaderLatch leaderLatch = new LeaderLatch(client,"/francis/leader");
        leaderLatch.addListener(new LeaderLatchListener(){
            public void isLeader() {
                System.out.println("I am Leader");
            }
            public void notLeader() {
                System.out.println("I am not Leader");
            }
        });
        leaderLatch.start();

        Thread.sleep(5000L);

        leaderLatch.close();
        client.close();
        server.close();
    }

}
