package com.kaiba.demo.loader;

/**
 * Created by luliru on 2016/8/8.
 */
public class FirstModule implements TeslaModule {

    public int getStartLevel() {
        return 0;
    }

    public void init(Object... args) throws Exception {
        System.out.println(this.toString() + "init");
    }

    public void start(Object... args) throws Exception {
        System.out.println(this.toString() + "init");
    }

    public void stop(Object... args) throws Exception {
        System.out.println(this.toString() + "stop");
    }
}
