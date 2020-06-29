package com.kaiba.demo.util;

public class ThreadUtils {

    public static void sleepNoInterrupt(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
