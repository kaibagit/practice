package com.kaiba.demo.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by luliru on 2016/8/10.
 */
public class AsynchronousFileCallbackTest {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        Path file = Paths.get("C:\\vas.txt");

        AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);

        ByteBuffer buffer = ByteBuffer.allocate(100_000);

        channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>(){
            public void completed(Integer result, ByteBuffer attachment){
                System.out.println("Bytes read [" + result + "]");
            }

            public void failed(Throwable exception, ByteBuffer attachment){
                System.err.println(exception.getMessage());
            }
        });
    }
}
