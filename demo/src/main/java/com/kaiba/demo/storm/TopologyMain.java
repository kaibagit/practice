package com.kaiba.demo.storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

/**
 * Created by kaiba on 2016/9/6.
 */
public class TopologyMain {

    public static void main(String[] args) throws InterruptedException {
        String filePath = "C:\\Users\\kaiba\\Documents\\Share\\workspace\\practice\\demo\\src\\main\\resources\\storm.txt";

        //定义拓扑
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("word-reader", new WordReader());
        builder.setBolt("word-normalizer", new WordNormalizer()).shuffleGrouping("word-reader");
//        builder.setBolt("word-counter", new WordCounter(),2).fieldsGrouping("word-normalizer", new Fields("word"));
        builder.setBolt("word-counter", new WordCounter(),2).shuffleGrouping("word-normalizer");

        //配置
        Config conf = new Config();
        conf.put("wordsFile", filePath);
        conf.setDebug(false);

        //运行拓扑
        conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("Getting-Started-Topologie", conf, builder.createTopology());
        Thread.sleep(1000);
        cluster.shutdown();
    }
}
