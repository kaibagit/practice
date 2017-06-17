package com.kaiba.demo.concurrent;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by luliru on 2017/3/13.
 */
class DelayedElement implements Delayed {

    private final long delayId;

    private final long expireMillisecond;

    public DelayedElement(long delay) {
        this.delayId = delay;
        expireMillisecond = System.currentTimeMillis() + delay;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert((expireMillisecond - System.currentTimeMillis()),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedElement element = (DelayedElement)o;
        return (int) (this.expireMillisecond - element.expireMillisecond);
    }

    @Override
    public String toString() {
        return "DelayedElement is " + delayId;
    }

}
