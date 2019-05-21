package com.kaiba.demo.concurrent.promise.wjj;

import com.google.common.collect.ImmutableMap;
import com.wjj.promise.IPromise;
import com.wjj.promise.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ExecutorService;

public class SetUp {

    private static final Logger log = LoggerFactory.getLogger(SetUp.class);

    public static void main(String[] args){
        startup();
    }

    public static void startup(){
        IPromise promise = new Promise.Builder().promiseHanler(executor -> {
            return 2*3;
        }).build().then(resolvedData -> {//返回一个新的promise1
            log.info(resolvedData.toString());
            return (Integer)resolvedData+1;
        }).then(res2->{
            log.info(res2.toString());
            //创建一个新的promise2并返回
            return new Promise.Builder().externalInput(res2).promiseHanler(executor -> {
                return (Integer)executor.getExternalInput()+2;
            }).build();
        }).then(res3->{
            log.info(res3.toString());
            return res3;
        });
    }

    public static void threadPool(){
        ExecutorService pool = Promise.pool(5,10);
        IPromise promise = new Promise.Builder().pool(pool).promiseHanler(handler->2*3).build();
    }

    public static void externalInput(){
        Map<String,String> map = ImmutableMap.of("name","张三");
        IPromise promise = new Promise.Builder().externalInput(map).promiseHanler(handler->{
            Map<String,String> m = (Map<String,String>)handler.getExternalInput();
            return "你好："+m.get("name");
        }).build();
    }

    public static void resolve(){
        new Promise.Builder().promiseHanler(handler->{
            int a = 2*3;
            handler.resolve(a);
            return null;
        }).build().listen(((resolvedData, e) -> {
            System.out.println(resolvedData);
        }));
    }

    public static void reject(){
        new Promise.Builder().promiseHanler(handler->{
            int a = 2*3;
            handler.resolve(a);
            throw new RuntimeException("err");
        }).build().listen(((resolvedData, e) -> {
            System.out.println(resolvedData);
            System.out.println(e==null);
        }));
    }

    public static void pCatch(){
        new Promise.Builder().promiseHanler(executor -> 3).build().then(resolvedData->{
            System.out.println("a:"+resolvedData);
            return new Promise.Builder().promiseHanler(executor -> {
                executor.reject(new RuntimeException("err"));
                return resolvedData;
            }).build();
        }).pCatch(e->{
            System.out.println("捕获到异常");
            return 3;
        }).then(resolvedData1 -> {
                    System.out.println("b:"+resolvedData1);
                    return "b:"+resolvedData1;
                },rejectReason -> {
                    System.err.println("c:"+rejectReason);
                }
        ).then(resolvedData2 -> {
                    System.out.println("d:"+resolvedData2);
                    return "d:"+resolvedData2;
                },rejectReason -> {
                    System.err.println("e:"+rejectReason);
                }
        );
    }

    public static void all(){
        IPromise p1 = new Promise.Builder().promiseHanler(executor -> {
            Thread.sleep(1000);
            return 1;
        }).build();
        IPromise p2 = new Promise.Builder().promiseHanler(executor -> {
            Thread.sleep(4000);
            return 2;
        }).build();
        IPromise p3 = new Promise.Builder().promiseHanler(executor -> {
            Thread.sleep(2000);
            return 3;
        }).build();
        Promise.all(p1,p2,p3).then(resolvedData -> {
            Object[] datas = (Object[])resolvedData;
            for(Object d:datas){
                System.out.println(d);
            }
            return null;
        },e->e.printStackTrace());
    }

    public static void waitAll(){
//        IPromise p1 = new Promise.Builder().promiseHanler(handler->2*3).build();
//        IPromise p2 = new Promise.Builder().promiseHanler(handler->{
//            throw new RuntimeException("手动抛出异常");
//        }).build();
//        IPromise p = Promise.waitAll(p1,p2).then(resolvedData -> {
//            Object[] datas = (Object[]) resolvedData;
//            for(Object d:datas){
//                if(d instanceof Throwable){
//                    ((Throwable)d).printStackTrace();
//                }else{
//                    System.out.println(d);
//                }
//            }
//            return datas;
//        });
    }
}