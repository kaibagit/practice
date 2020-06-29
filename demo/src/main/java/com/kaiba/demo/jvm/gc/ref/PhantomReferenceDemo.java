package com.kaiba.demo.jvm.gc.ref;

import com.kaiba.demo.util.ThreadUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

@Slf4j
public class PhantomReferenceDemo {

    public static void main(String[] args) {
        ReferenceQueue<MyBean> queue = new ReferenceQueue<>();
        new Thread(()->{
            while (true) {
                try {
                    MyPhantomReference ref = (MyPhantomReference) queue.remove();
                    log.info("{}:{} enqueued.",ref.i,ref);
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
                MyPhantomReference ref = new MyPhantomReference<>(i,mybean,queue);
                mybean = null;
                log.info("第一次gc before");
                System.gc();
                log.info("第一次gc after");
                ThreadUtils.sleepNoInterrupt(1000L);
                // 如果MyBean重写了finalize()，则必须加再执行此System.gc()才会enqueue
//                log.info("第二次gc after");
//                System.gc();
//                log.info("第二次次gc after");
            }
        }).start();
        log.info("start");
    }

    static class MyPhantomReference<T> extends PhantomReference<T> {
        private int i;
        public MyPhantomReference(int i,T referent, ReferenceQueue<? super T> q) {
            super(referent, q);
            this.i = i;
        }
    }
}
