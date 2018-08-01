package com.luliru.practice.netty.demo.netty4.pool;

import io.netty.channel.Channel;
import io.netty.channel.pool.ChannelPoolHandler;

import java.util.concurrent.atomic.AtomicInteger;

final class CountingChannelPoolHandler implements ChannelPoolHandler {
    private final AtomicInteger channelCount = new AtomicInteger(0);
    private final AtomicInteger acquiredCount = new AtomicInteger(0);
    private final AtomicInteger releasedCount = new AtomicInteger(0);

    @Override
    public void channelCreated(Channel ch) {
        channelCount.incrementAndGet();
    }

    @Override
    public void channelReleased(Channel ch) {
        releasedCount.incrementAndGet();
    }

    @Override
    public void channelAcquired(Channel ch) {
        acquiredCount.incrementAndGet();
    }

    public int channelCount() {
        return channelCount.get();
    }

    public int acquiredCount() {
        return acquiredCount.get();
    }

    public int releasedCount() {
        return releasedCount.get();
    }
}
