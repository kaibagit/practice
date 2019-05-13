package com.luliru.vertx.demo.cluster.zookeeper;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.zookeeper.ZookeeperClusterManager;

public class ZkClusterSetup {

    public static void main(String[] args){
        JsonObject zkConfig = new JsonObject();
        // 设置相关配置项
        zkConfig.put("zookeeperHosts", "127.0.0.1");// zk host 地址
        zkConfig.put("rootPath", "io.vertx");// 根路径
        zkConfig.put("retry", new JsonObject() // 重试策略
                .put("initialSleepTime", 3000)
                .put("maxTimes", 3));

        ClusterManager mgr = new ZookeeperClusterManager(zkConfig);//创建 ZookeeperClusterManager
        VertxOptions options = new VertxOptions().setClusterManager(mgr);// 设置集群管理器
        Vertx.clusteredVertx(options, res -> {
            if (res.succeeded()) {
                Vertx vertx = res.result();
                System.out.println(vertx);
            } else {
                // failed!
                res.cause().printStackTrace();
            }
        });
    }
}
