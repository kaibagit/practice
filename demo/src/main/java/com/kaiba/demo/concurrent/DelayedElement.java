package com.kaiba.demo.concurrent;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by luliru on 2017/3/13.
 */
class DelayedElement implements Delayed {

    private final long delay;

    private final long expire;

    public DelayedElement(long delay) {
        this.delay = delay;
        expire = System.currentTimeMillis() + delay;
    }

    @Override

    public long getDelay(TimeUnit unit) {
        return expire - System.currentTimeMillis();
    }

    @Override

    public int compareTo(Delayed o) {
        return (int) (this.delay - o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override

    public String toString() {
        return "DelayedElement is " + delay;
    }

}
