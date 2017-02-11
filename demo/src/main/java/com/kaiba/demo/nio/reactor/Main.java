package com.kaiba.demo.nio.reactor;

import java.io.IOException;

/**
 * Created by luliru on 2017/2/3.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        NioReactor nioReactor = new NioReactor();
        nioReactor.bind(10000).start();
    }
}
