package com.kaiba.demo.concurrent.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by luliru on 2017/7/4.
 */
public class SeparateIOPool {

    private static final Logger log = LoggerFactory.getLogger(SeparateIOPool.class);

    public static void main(String[] args) throws IOException {
        CompletableFuture.supplyAsync(SeparateIOPool::getFileName)
                .thenApplyAsync(SeparateIOPool::readDiskBlocking, DISK_1)
                .thenAcceptAsync(SeparateIOPool::writeLanBlocking, LAN_1)
                .thenRunAsync(SeparateIOPool::notifyUser);
        System.in.read();
    }

    public static String getFileName(){
        log.info("getFileName");
        return "hello.txt";
    }

    public static String readDiskBlocking(String val){
        log.info("readDiskBlocking");
        return "==========="+val;
    }

    public static void writeLanBlocking(String val){
        log.info("writeLanBlocking="+val);
    }

    public static void notifyUser(){
        log.info("notifyUser");
    }

    static Executor DISK_1 = Executors.newSingleThreadExecutor(r -> new Thread(r, "DISK_1"));

    static Executor LAN_1 = Executors.newSingleThreadExecutor(r -> new Thread(r, "LAN_1"));
}
