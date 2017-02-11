package com.kaiba.demo.nio.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by luliru on 2017/2/10.
 */
public class Buffer {

    private Queue<ByteBuffer> writeByteBuffers = new LinkedList<>();

    private int totalBytes = 0;

    public void read(byte[] bytes){
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        writeByteBuffers.add(byteBuffer);
        totalBytes += bytes.length;
    }

    public int getRemains(){
        return totalBytes;
    }

    public void flush(SocketChannel socketChannel) throws IOException {
        while(totalBytes > 0){
            ByteBuffer firstBuffer = writeByteBuffers.element();
            if(firstBuffer.position() == firstBuffer.limit()){
                firstBuffer.flip();
            }
            int count = socketChannel.write(firstBuffer);
            if(count == 0){
                break;
            }else if(count > 0){
                totalBytes = totalBytes - count;
                if(firstBuffer.remaining() == 0){
                    writeByteBuffers.poll();
                }
            }
        }
    }
}
