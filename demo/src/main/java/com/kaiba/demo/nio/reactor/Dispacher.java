package com.kaiba.demo.nio.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by luliru on 2017/2/4.
 */
public class Dispacher implements Handler {

    public Dispacher(){
    }

    @Override
    public void accept(ChannelContext ctx) {
        throw new RuntimeException("not support accept");
    }

    @Override
    public void read(ChannelContext ctx) throws IOException {
        //持续将数据读入buffer
        while(ctx.read() > 0);
        //处理业务
        bizHandler(ctx,ctx.getReadBuffer());
        //将buffer中的业务数据，写入channel
        ctx.flush();
    }

    @Override
    public void write(ChannelContext ctx) {

    }

    private void bizHandler(ChannelContext ctx, ByteBuffer buffer) throws IOException {
        System.out.println("bizHandler,Thread="+Thread.currentThread()+";p="+buffer.position());
        if(buffer.position() > 10){
            buffer.flip();
            byte[] bytes = new byte[10];
            buffer.get(bytes,0,10);
            buffer.compact();
            System.out.println(new String(bytes));
            ctx.write(bytes);
        }
    }
}
