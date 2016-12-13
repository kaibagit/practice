package com.kaiba.demo.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by luliru on 2016/12/13.
 */
public class PipeTest {

    public static void main(String[] args) throws IOException {
        final PipedOutputStream outputStream = new PipedOutputStream();
        final PipedInputStream inputStream = new PipedInputStream(outputStream);

        Thread thread1 = new Thread(){
            public void run(){
                try {
                    outputStream.write("hello world".getBytes());
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread2 = new Thread(){
            public void run(){
                try {
                    int data = inputStream.read();
                    while(data != -1){
                        System.out.print((char)data);
                        data = inputStream.read();
                    }
                    System.out.print("\n");
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        thread1.start();
        thread2.start();
    }
}
