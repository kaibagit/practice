package com.luliru.test.hessian;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by luliru on 2017/3/20.
 */
public class ExceptionTest {

    public static void main(String[] args) throws IOException {
        NullPointerException e = new NullPointerException("This is a demo");

        //序列化
        FileOutputStream os = new FileOutputStream("exception.out");
        HessianOutput ho = new HessianOutput(os);
        ho.writeObject(e);

        //反序列化
        FileInputStream is = new FileInputStream("exception.out");
        HessianInput hi = new HessianInput(is);
        Object o = hi.readObject();
        System.out.println(o);

    }
}
