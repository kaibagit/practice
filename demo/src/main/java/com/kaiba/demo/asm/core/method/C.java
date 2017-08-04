package com.kaiba.demo.asm.core.method;

/**
 * Created by luliru on 2017/7/25.
 */
public class C {

    public void m() throws Exception {
        Thread.sleep(100);
    }

}

//public class C {
//    public static long timer;
//
//    public void m() throws Exception {
//        timer -= System.currentTimeMillis();
//        Thread.sleep(100);
//        timer += System.currentTimeMillis();
//    }
//}
