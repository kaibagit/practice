//package com.luliru.test.log;
//
//import org.apache.log4j.Logger;
//import org.apache.log4j.NDC;
//
///**
// * Created by kaiba on 2016/6/5.
// */
//public class NDCTest {
//
//    private static Logger logger = Logger.getLogger(NDCTest.class);
//
//    public static void main(String[] args){
//        //NDC采用栈的机制存储上下文，是线程独立的，子线程会从父线程拷贝上下文。
//        NDC.push("NDC one");
//        NDC.push("NDC two");
//        //删除栈顶消息
//        NDC.pop();
//        //清除全部的消息，必须再线程退出前显示的调用，否则会导致内存溢出。
//        NDC.remove();
//
//        logger.info("info");
//    }
//}
