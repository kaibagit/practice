package com.luliru.netty4;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by luliru on 2017/4/27.
 */
public class NettyTimerTask {

    private static final Logger log = LoggerFactory.getLogger(NettyTimerTask.class);

    //定义HashedWheelTimer对象
    //5000毫秒跳到下一个solt,一共有512个solt（默认为512个solt）
    static HashedWheelTimer timer=new HashedWheelTimer(5000L, TimeUnit.MILLISECONDS, 512);

    public static void main(String[] args) {
        TimerTask task = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                log.error("---run service-----");
                //任务执行完成后再把自己添加到任务solt上
                addTask(this);
            }
        };
        addTask(task);
    }

    public static void addTask(TimerTask task){
        //根据时长把task任务放到响应的solt上
        timer.newTimeout(task, 500, TimeUnit.MILLISECONDS);
    }
}
