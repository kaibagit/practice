package com.luliru.test.zk;

/**
 * Created by kaiba on 2016/6/26.
 */
public class ThreadUtil {

    public static void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
