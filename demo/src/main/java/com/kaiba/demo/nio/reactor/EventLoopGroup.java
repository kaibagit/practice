package com.kaiba.demo.nio.reactor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by luliru on 2017/2/13.
 */
public class EventLoopGroup {

    private ExecutorService executor = Executors.newFixedThreadPool(4);

    void execute(Runnable command){
        executor.execute(command);
    }
}
