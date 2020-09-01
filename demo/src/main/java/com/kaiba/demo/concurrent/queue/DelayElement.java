package com.kaiba.demo.concurrent.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by luliru on 2017/3/13.
 */
class DelayElement implements Delayed {

    private final long delayId;

    private final long expireMillisecond;

    public DelayElement(long delay) {
        this.delayId = delay;
        expireMillisecond = System.currentTimeMillis() + delay;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert((expireMillisecond - System.currentTimeMillis()),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayElement element = (DelayElement)o;
        return (int) (this.expireMillisecond - element.expireMillisecond);
    }

    @Override
    public String toString() {
        return "DelayedElement is " + delayId;
    }

}
