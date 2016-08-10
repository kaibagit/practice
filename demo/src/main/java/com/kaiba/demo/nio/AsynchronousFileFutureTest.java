package com.kaiba.demo.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by luliru on 2016/8/10.
 */
public class AsynchronousFileFutureTest {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        Path file = Paths.get("C:\\vas.txt");

        AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);

        ByteBuffer buffer = ByteBuffer.allocate(100_000);
        Future<Integer> result = channel.read(buffer, 0);

        while(!result.isDone()){
            // do something
        }

        Integer bytesRead = result.get();
        System.out.println("Bytes read [" + bytesRead + "]");
    }
}
