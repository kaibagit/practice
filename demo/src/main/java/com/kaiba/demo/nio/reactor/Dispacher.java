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
//        int i = ctx.read();
//        while (i > 0){
//            System.out.println("ctx:"+ctx+"; buf:"+ctx.getReadBuffer()+"; i:"+i+";p="+ctx.getReadBuffer().position());
//            i = ctx.read();
//        }
        while(ctx.read() > 0);
        readBiz(ctx,ctx.getReadBuffer());
    }

    @Override
    public void write(ChannelContext ctx) {

    }

    private void readBiz(ChannelContext ctx,ByteBuffer buffer) throws IOException {
        System.out.println("readBiz,Thread="+Thread.currentThread()+";p="+buffer.position());
        if(buffer.position() > 10){
            buffer.flip();
            byte[] bytes = new byte[10];
            buffer.get(bytes,0,10);
            buffer.compact();
            System.out.println(new String(bytes));
            ctx.write(bytes);
            ctx.flush();
        }
    }
}
