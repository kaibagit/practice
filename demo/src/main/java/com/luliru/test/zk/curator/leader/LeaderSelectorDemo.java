package com.luliru.test.zk.curator.leader;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.CancelLeadershipException;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

/**
 * Created by kaiba on 2016/10/7.
 */
public class LeaderSelectorDemo {

    public static void main(String[] args) throws Exception {
        TestingServer server = new TestingServer();

        CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), new ExponentialBackoffRetry(1000, 3));
        LeaderSelector leaderSelector = new LeaderSelector(client, "/examples/leader", new LeaderSelectorListener() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                System.out.println("I am Leader");
                Thread.sleep(1000L);
            }

            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                if(connectionState == ConnectionState.SUSPENDED || connectionState == ConnectionState.LOST) {
                    throw new CancelLeadershipException();
                }
            }
        });
        leaderSelector.autoRequeue();

        client.start();
        leaderSelector.start();

        Thread.sleep(5000L);

        leaderSelector.close();
        client.close();
        server.close();
    }
}
