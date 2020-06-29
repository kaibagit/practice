package com.kaiba.demo.jvm.gc.ref;

import com.kaiba.demo.util.ThreadUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

@Slf4j
public class WeakReferenceDemo {

    /**
     * 先触发finalize，再enqueued
     * @param args
     */
    public static void main(String[] args) {
        ReferenceQueue<MyBean> queue = new ReferenceQueue<>();
        new Thread(()->{
            while (true) {
                try {
                    MyWeakReference ref = (MyWeakReference) queue.remove();
                    log.info("{}:{} enqueued.",ref.i,ref.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            int i = 0;
            while (true) {
                i++;
                MyBean mybean = new MyBean(i);
                MyWeakReference ref = new MyWeakReference<>(i,mybean,queue);
                mybean = null;
                System.gc();
                ThreadUtils.sleepNoInterrupt(1000L);
            }
        }).start();
        log.info("start");
    }

    static class MyWeakReference<T> extends WeakReference<T> {
        private int i;
        public MyWeakReference(int i,T referent, ReferenceQueue<? super T> q) {
            super(referent, q);
            this.i = i;
        }
    }
}
