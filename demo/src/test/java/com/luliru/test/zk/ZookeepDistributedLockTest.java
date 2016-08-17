package com.luliru.test.zk;

/**
 * Created by luliru on 2016/8/17.
 */
public class ZookeepDistributedLockTest {

    public static void main(String[] args){
        ZookeepDistributedLock lock = new ZookeepDistributedLock();
        lock.init();

        lock.lock();
        lock.unlock();

        lock.destroy();
    }

}
