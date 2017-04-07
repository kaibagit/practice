package com.kaiba.demo.concurrent.lock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * CLH锁也是一种基于链表的可扩展、高性能、公平的自旋锁，申请线程只在本地变量上自旋，它不断轮询前驱的状态，如果发现前驱释放了锁就结束自旋。
 * Created by luliru on 2017/4/7.
 */
public class CLHLock {

    private static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> UPDATER = AtomicReferenceFieldUpdater
            . newUpdater(CLHLock.class, CLHNode .class , "tail" );

    private volatile CLHNode tail ;

    public void lock(CLHNode currentThreadCLHNode) {
        CLHNode preNode = UPDATER.getAndSet( this, currentThreadCLHNode); //把this里的"tail" 值设置成currentThreadCLHNode
        if(preNode != null) {//已有线程占用了锁，进入自旋
            while(preNode.isLocked ) {
            }
        }
    }

    public void unlock(CLHNode currentThreadCLHNode) {
        // 如果队列里只有当前线程，则释放对当前线程的引用（for GC）。
        if (!UPDATER .compareAndSet(this, currentThreadCLHNode, null)) {
            // 还有后续线程
            currentThreadCLHNode. isLocked = false ;// 改变状态，让后续线程结束自旋
        }
    }

    public static class CLHNode {
        private boolean isLocked = true; // 默认是在等待锁
    }
}
