package com.kaiba.demo.apache.common.pool;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by luliru on 2016/10/17.
 */
public class CustomizedObjectFactory extends BasePoolableObjectFactory {

    private AtomicInteger index = new AtomicInteger(1);

    @Override
    public Object makeObject() throws Exception {
        CustomizedObject object = new CustomizedObject(index.getAndIncrement());
        return object;
    }

    public void destroyObject(Object obj) throws Exception  {
    }

    public boolean validateObject(Object obj) {
        if(obj instanceof CustomizedObject){
            return true;
        }
        return false;
    }

    static class CustomizedObject{
        private int id;

        public CustomizedObject(int id){
            this.id = id;
            System.out.println("make "+id);
        }

        public int getId() {
            return id;
        }
    }

    public static void main(String[] args) throws Exception {
        GenericObjectPool.Config config = new GenericObjectPool.Config();
        config.minIdle = 4;
        config.maxActive = 16;
        config.maxWait = 30000;
        GenericObjectPool poll = new GenericObjectPool(new CustomizedObjectFactory(),config);
        for(int i=0;i< 8;i++){
            CustomizedObject obj = (CustomizedObject)poll.borrowObject();
            System.out.println(obj.getId());
            poll.returnObject(obj);
        }
    }
}
