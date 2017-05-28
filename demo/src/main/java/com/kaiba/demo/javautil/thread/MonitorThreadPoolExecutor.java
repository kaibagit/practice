package com.kaiba.demo.javautil.thread;

import java.util.concurrent.*;

/**
 * Created by luliru on 2017/5/28.
 */
class MonitorThreadPoolExecutor extends ThreadPoolExecutor {

    public MonitorThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,
                                     TimeUnit unit,BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    protected void beforeExecute(Thread paramThread, Runnable paramRunnable) {
        System. out.println("efore:" + paramThread.getName());
    }

    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        System. out.println("work_task after worker thread is :" + r);

    }

    protected void terminated() {
        System. out.println("terminated getCorePoolSize:" + this.getCorePoolSize() + "；getPoolSize:" + this.getPoolSize() + "；getTaskCount:" + this .getTaskCount() + "；getCompletedTaskCount:"
        + this.getCompletedTaskCount() + "；getLargestPoolSize:" + this.getLargestPoolSize() + "；getActiveCount:" + this.getActiveCount());
        System. out.println("ThreadPoolExecutor terminated:" );
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(500L);// 方便测试
        ExecutorService executor = new MonitorThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS , new LinkedBlockingQueue() );
        for (int i = 0; i < 3; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        Thread. sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executor.execute(runnable);
        }
        executor.shutdown();
        System. out.println("Thread Main End!");
    }

}
