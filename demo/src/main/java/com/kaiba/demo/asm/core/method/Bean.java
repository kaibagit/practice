package com.kaiba.demo.asm.core.method;

/**
 * Created by luliru on 2017/7/23.
 */
public class Bean {
    private int f;
    public int getF() {
        return this.f;
    }
    public void setF(int f) {
        this.f = f;
    }

    public void checkAndSetF(int f) {
        if (f >= 0) {
            this.f = f;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void test(){
        int i = 445;
        int j = f + i;
        System.out.println(j);
    }

}