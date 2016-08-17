package com.luliru.test.zk;

import org.apache.zookeeper.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by luliru on 2016/8/17.
 */
public class ZookeepDistributedLock implements Lock {

    private ZooKeeper zk;

    private String selfNode;

    private Object lockObject = new Object();

    public void init(){
        try{
            zk = new ZooKeeper("127.0.0.1:2181", 500000,new Watcher() {
                // 监控所有被触发的事件
                public void process(WatchedEvent event) {
                    System.out.println("default:"+event);
                    if (event.getState() == Event.KeeperState.SyncConnected){
                        Event.EventType type = event.getType();
                        if(type == Event.EventType.None){
                            System.out.println("connection success.");
                        }else if(type == Event.EventType.NodeDeleted){
                            System.out.println("node eleted");
                        }
                    }
                }
            });
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void destroy(){
        try{
            zk.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void lock() {
        try{
            String path = zk.create("/lock/","data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            selfNode = path.replace("/lock/","");
            System.out.println(selfNode);

            while(true){
                synchronized (lockObject){
                    List<String> subNodes = zk.getChildren("/lock", new Watcher() {
                        @Override
                        public void process(WatchedEvent event) {
                            System.out.println(event);
                            if (event.getState() == Event.KeeperState.SyncConnected){
                                Event.EventType type = event.getType();
                                if(type == Event.EventType.None){
                                    System.out.println("connection success.");
                                }else if(type == Event.EventType.NodeDeleted){
                                    System.out.println("node eleted");
                                    lockObject.notifyAll();
                                }
                            }
                        }
                    });
                    String maxOfLessThanSelfNode = getMaxOfLessThanSelfNode(subNodes);
                    if(maxOfLessThanSelfNode != null){
                        try{
                            lockObject.wait();
                        }catch (InterruptedException e){
                            System.out.println("sleep up");
                        }
                    }else{
                        break;
                    }
                }

            }
            System.out.println("lock success");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        try{
            zk.delete("/lock/"+selfNode,-1);
            selfNode = null;
            System.out.println("unlock success");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    private String getMaxOfLessThanSelfNode(List<String> subNodes){
        String maxOfLessThanSelfNode = null;
        for(String subNode:subNodes){
            if(subNode.compareTo(selfNode) < 0 ){
                if(maxOfLessThanSelfNode == null){
                    maxOfLessThanSelfNode = subNode;
                }else if(subNode.compareTo(maxOfLessThanSelfNode) > 0){
                    maxOfLessThanSelfNode = subNode;
                }
            }
        }
        return maxOfLessThanSelfNode;
    }

}
