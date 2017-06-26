package com.kaiba.demo.guava;

import com.google.common.util.concurrent.*;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * Created by luliru on 2017/6/26.
 */
public class ListenableFutureDemo {

    public static void main(String[] args) throws InterruptedException {
        transform();
    }

    private static void test() throws InterruptedException {
        ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

        final ListenableFuture<String> future = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000 * 2);
                return "Task done !";
            }
        });

        future.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    final String contents = future.get();
                    System.out.println(contents);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }, MoreExecutors.sameThreadExecutor());

        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });

        Thread.sleep(5 * 1000);  //wait for task done

        pool.shutdown();
    }

    private static void parallel(){
        ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        final long start = System.currentTimeMillis();
        ListenableFuture<Object> taskFuture1 = pool.submit(new Task1());
        ListenableFuture<Object> taskFuture2 = pool.submit(new Task2());
        ListenableFuture<Object> taskFuture3 = pool.submit(new Task3());

        ListenableFuture<List<Object>> successfulQueries = Futures
                .successfulAsList(taskFuture1, taskFuture2, taskFuture3);
        Futures.addCallback(successfulQueries, new FutureCallback<Object>() {
            public void onFailure(Throwable arg0) {
                System.out.println(arg0);
            }

            public void onSuccess(Object arg0) {
                System.out.println(arg0);
                System.out.println("parallel:"
                        + (System.currentTimeMillis() - start));
            }
        });
    }

    public static void transform(){
        final CountDownLatch latch = new CountDownLatch(1);
        final ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

        ListenableFuture<String> explosion = service.submit(new Callable<String>() {
            public String call() throws Exception {
                System.out.println("任务线程正在执行...");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "任务线程的结果";
            }
        });

        ListenableFuture<String> first = Futures.transform(explosion, new AsyncFunction<String, String>() {
            public ListenableFuture<String> apply(final String input) throws Exception {
                ListenableFuture<String> temp = service.submit(new Callable<String>() {
                    public String call() throws Exception {
                        System.out.println("第1个回调线程正在执行...");
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return input + " & 第1个回调线程的结果 ";
                    }
                });
                return temp;
            }
        }, service);


        ListenableFuture<String> second = Futures.transform(first, new AsyncFunction<String, String>() {
            public ListenableFuture<String> apply(final String input) throws Exception {
                ListenableFuture<String> temp = service.submit(new Callable<String>() {
                    public String call() throws Exception {
                        System.out.println("第2个回调线程正在执行...");
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return input + " & 第2个回调线程的结果 ";
                    }
                });
                return temp;
            }
        }, service);

        ListenableFuture<String> third = Futures.transform(second, new AsyncFunction<String, String>() {
            public ListenableFuture<String> apply(final String input) throws Exception {
                ListenableFuture<String> temp = service.submit(new Callable<String>() {
                    public String call() throws Exception {
                        System.out.println("第3个回调线程正在执行...");
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return input + " & 第3个回调线程的结果 ";
                    }
                });
                return temp;
            }
        }, service);

        ListenableFuture<String> forth = Futures.transform(third, new AsyncFunction<String, String>() {
            public ListenableFuture<String> apply(final String input) throws Exception {
                ListenableFuture<String> temp = service.submit(new Callable<String>() {
                    public String call() throws Exception {
                        System.out.println("第4个回调线程正在执行...");
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return input + " & 第4个回调线程的结果 ";
                    }
                });
                return temp;
            }
        }, service);

        Futures.addCallback(forth, new FutureCallback<String>() {
            public void onSuccess(String result) {
                latch.countDown();
                System.out.println("结果: " + result);
            }

            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }



    public static class Task1 implements Callable<Object> {

        public Object call() throws Exception {
            Thread.sleep(5000);
            return "Task1";
        }
    }

    public static class Task2 implements Callable<Object> {

        public Object call() throws Exception {
            Thread.sleep(3000);
            return "Task2";
        }
    }

    public static class Task3 implements Callable<Object> {

        public Object call() throws Exception {
            Thread.sleep(4000);
            return 3;
        }
    }
}
