package com.kaiba.demo.util.slidingwindow;

import java.util.concurrent.TimeUnit;

/**
 * Loops
 * Created by luliru on 2019-06-18.
 */
public class Loops {

    public static void dieLoop(Runnable runnable) {
        while (true) {
            run(runnable);
        }
    }

    public static void rateLoop(Runnable runnable, int mills) {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(mills);
            } catch (InterruptedException e) {

            }
            run(runnable);
        }
    }

    public static void fixLoop(Runnable runnable, int loop) {
        for (int i = 0; i < loop; i++) {
            run(runnable);
        }
    }

    private static void run(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}