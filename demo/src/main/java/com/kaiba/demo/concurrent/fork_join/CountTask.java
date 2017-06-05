package com.kaiba.demo.concurrent.fork_join;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by luliru on 2017/6/4.
 */
public class CountTask extends RecursiveTask<Integer> {

    private static final Logger log = LoggerFactory.getLogger(CountTask.class);

    //阈值
    private static final int THRESHOLD = 2;
    //起始值
    private int start;
    //结束值
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Integer compute() {
        boolean compute = (end - start) <= THRESHOLD;
        int res = 0;
        if (compute){
            for (int i = start; i <= end; i++){
                res += i;
                try {
                    Thread.sleep(1000L);    //假设每步计算耗时1s
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }else {
            //如果长度大于阈值，则分割为小任务
            int mid = (start + end) / 2;
            CountTask task1 = new CountTask(start,mid);
            CountTask task2 = new CountTask(mid + 1, end);
            //计算小任务的值
            task1.fork();
            task2.fork();
            //得到两个小任务的值
            int task1Res = task1.join();
            int task2Res = task2.join();
            res = task1Res + task2Res;
        }
        return res;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        CountTask task = new CountTask(1,100);
        log.info("开始");
        ForkJoinTask<Integer> submit = pool.submit(task);
        log.info("Final result:" + submit.get());
    }
}
