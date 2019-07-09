package com.luliru.test.hessian;

import com.caucho.hessian.io.HessianOutput;
import com.luliru.test.hessian.bean.TestObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by luliru on 2018/3/14.
 */
public class NodeTest {

    public static void main(String[] args) throws IOException {
        Map map = new HashMap<>();
        map.put("a",1);
        map.put("b",true);
        map.put("c","string");

        TestObject obj = new TestObject();
        obj.setA(1);
        obj.setB("string");
        obj.setC(true);
        obj.setD(1.1f);
        obj.setE(22440L);
        obj.setF(Arrays.asList(1,2,3,'4',true,5));
        obj.setG(map);

        FileOutputStream os = new FileOutputStream("node.txt");
        HessianOutput ho = new HessianOutput(os);
        ho.writeObject(obj);
    }
}
