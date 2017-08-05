package com.kaiba.demo.asm.tools;

import org.objectweb.asm.util.ASMifier;

/**
 * Created by luliru on 2017/8/5.
 */
public class ASMifierTest {

    public static void main(String[] args) throws Exception {
        String[] arr = {"java.lang.Runnable"};
        ASMifier.main(arr);
    }
}
