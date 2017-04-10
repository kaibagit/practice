package com.kaiba.demo.concurrent.lock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * MCS Spinlock 是一种基于链表的可扩展、高性能、公平的自旋锁，申请线程只在本地变量上自旋，直接前驱负责通知其结束自旋，从而极大地减少了不必要的处理器缓存同步的次数，降低了总线和内存的开销。<br>
 * 每个Node都指向下一个Node，当上一个Node的isLocked=false时，获得锁，否则自旋<br>
 * 释放锁时，将isLocked改为false，取消对后续Node的引用
 * Created by luliru on 2017/4/10.
 */
public class MCSLock {

    private static final AtomicReferenceFieldUpdater<MCSLock, MCSNode> UPDATER = AtomicReferenceFieldUpdater
            . newUpdater(MCSLock.class, MCSNode. class, "queue" );

    volatile MCSNode queue ;// 指向最后一个申请锁的MCSNode

    public void lock(MCSNode currentThreadMcsNode) {
        MCSNode predecessor = UPDATER.getAndSet(this, currentThreadMcsNode);// step 1
        if (predecessor != null) {
            predecessor.next = currentThreadMcsNode;// step 2

            while (currentThreadMcsNode.isLocked ) {// step 3
            }
        }
    }

    public void unlock(MCSNode currentThreadMcsNode) {
        if ( UPDATER.get( this ) == currentThreadMcsNode) {// 锁拥有者进行释放锁才有意义
            if (currentThreadMcsNode.next == null) {// 检查是否有人排在自己后面
                if (UPDATER.compareAndSet(this, currentThreadMcsNode, null)) {// step 4
                    // compareAndSet返回true表示确实没有人排在自己后面
                    return;
                } else {
                    // 突然有人排在自己后面了，可能还不知道是谁，下面是等待后续者
                    // 这里之所以要忙等是因为：step 1执行完后，step 2可能还没执行完
                    while (currentThreadMcsNode.next == null) { // step 5
                    }
                }
            }

            currentThreadMcsNode.next.isLocked = false;
            currentThreadMcsNode.next = null;// for GC
        }
    }

    public static class MCSNode {
        volatile MCSNode next;
        volatile boolean isLocked = true; // 默认是在等待锁
    }
}
