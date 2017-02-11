package com.kaiba.demo.nio.reactor;

import java.io.IOException;
import java.nio.channels.SelectableChannel;

/**
 * Created by luliru on 2017/2/3.
 */
public interface Handler {

    public void accept(ChannelContext ctx) throws IOException;

    public void read(ChannelContext ctx) throws IOException;

    public void write(ChannelContext ctx);
}
