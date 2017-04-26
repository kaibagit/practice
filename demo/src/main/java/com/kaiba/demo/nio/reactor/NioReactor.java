package com.kaiba.demo.nio.reactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by luliru on 2017/2/3.
 */
public class NioReactor{

    private static final Logger log = LoggerFactory.getLogger(NioReactor.class);

    //-1：已结束 0：未开始 1：进行中
    private static final int UNSTARTED = 0;

    private static final int RUNNING = 1;

    private static final int OVER = -1;

    private Selector selector;

    private int port;

    private int workerThreadNum = 4;

    private volatile int status = UNSTARTED;

    private Acceptor acceptor = new Acceptor();

    private Dispacher dispacher = new Dispacher();

    private ExecutorService executor = Executors.newFixedThreadPool(4);

    public NioReactor() throws IOException {
    }

    public NioReactor bind(int port) {
        this.port = port;
        return this;
    }

    public NioReactor setWorkers(int workerThreadNum) {
        this.workerThreadNum = workerThreadNum;
        return this;
    }

    public void start() throws IOException {
        executor = Executors.newFixedThreadPool(workerThreadNum);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(), 10000);
        serverSocketChannel.socket().bind(address);
        serverSocketChannel.configureBlocking(false);

        selector = Selector.open(); // 创建Selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // 注册ServerSocket通道上的Accept事件

        status = RUNNING;

        Runnable selectRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    while(isRunning()){
                        if (selector.select() > 0) { //这个方法会阻塞，直到至少有一个已注册的事件发生。当一个或者更多的事件发生时，select()方法将返回所发生的事件的数量。
                            Set<SelectionKey> selectedKeys = selector.selectedKeys(); // 获取发生的事件
                            Iterator<SelectionKey> it = selectedKeys.iterator(); // 依次进行处理

                            //对于同一个channel，只会绑定一个线程去执行
                            while (it.hasNext()) {
                                SelectionKey key = it.next();
                                if (key.isAcceptable()) { // 如果是Accept事件
                                    final ChannelContext ctx = new ChannelContext(key);
                                    acceptor.accept(ctx);
                                }
                                if (key.isReadable()) {
                                    final ChannelContext ctx = (ChannelContext) key.attachment();
//                                    key.interestOps()
                                    Runnable run = new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                dispacher.read(ctx);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    };
//                                    Thread.sleep(1000L);
//                                    executor.execute(run);
                                }
                                if (key.isWritable()) {
                                    final ChannelContext ctx = (ChannelContext) key.attachment();
                                    Runnable run = new Runnable() {
                                        @Override
                                        public void run() {
                                            dispacher.write(ctx);
                                        }
                                    };
                                    executor.execute(run);
                                }
                                it.remove(); // 需要将处理过的事件移除，如果我们没有删除处理过的键，那么它仍然会在主集合中以一个激活的键出现，这会导致我们尝试再次处理它。
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(selectRunnable).start();
    }

    public boolean isRunning(){
        return status == RUNNING;
    }

    public void shutdown() {

    }
}
