package com.kaiba.demo.jvm.stack;

import java.io.Serializable;

/**
 * Created by luliru on 2016/8/9.
 */
public abstract class Model<T> {

    public static <T> T findById(Serializable id){
        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String modelName = stackTrace[3].getClassName();
            Class clazz = Class.forName(modelName);
            Object obj = clazz.newInstance();
            return (T)obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
