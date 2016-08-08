package com.kaiba.demo.loader;

/**
 * Created by luliru on 2016/8/8.
 */
public class SecondModule implements TeslaModule{
    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public void init(Object... args) throws Exception {
        System.out.println(this.toString() + "init");
    }

    @Override
    public void start(Object... args) throws Exception {
        System.out.println(this.toString() + "start");
    }

    @Override
    public void stop(Object... args) throws Exception {
        System.out.println(this.toString() + "stop");
    }
}
