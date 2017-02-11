package com.kaiba.demo.nio.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by luliru on 2017/2/6.
 */
public class ChannelContext {

    private SelectionKey selectionKey;

    private Selector selector;

    private SelectableChannel channel;

    private Handler handler;

    private Object attachment;

    private ByteBuffer readBuffer;

    private Buffer writeBuffer = new Buffer();

    public ChannelContext(SelectionKey selectionKey){
        this.selectionKey = selectionKey;
        this.channel = selectionKey.channel();
        this.selector = selectionKey.selector();
    }

    public SelectableChannel getChannel() {
        return channel;
    }

    public Selector getSelector(){
        return selector;
    }

    public void setAttachment(Object attachment){
        this.attachment = attachment;
    }

    public Object getAttachment(){
        return this.attachment;
    }

    public int read() throws IOException {
        if(readBuffer == null){
            readBuffer = ByteBuffer.allocate(1024);
        }
        SocketChannel socketChannel = (SocketChannel)channel;
        return socketChannel.read(readBuffer);
    }

    public ByteBuffer getReadBuffer(){
        return readBuffer;
    }

    public void write(byte[] bytes){
        writeBuffer.read(bytes);
    }

    public boolean writeable(){
        return writeBuffer.getRemains() > 0;
    }

    public void flush() throws IOException {
        System.out.println(Thread.currentThread());
        SocketChannel socketChannel = (SocketChannel)channel;
        writeBuffer.flush(socketChannel);
        if(writeBuffer.getRemains() > 0){
            socketChannel.register(selector,selectionKey.interestOps() | SelectionKey.OP_WRITE,this);
        }
    }
}
