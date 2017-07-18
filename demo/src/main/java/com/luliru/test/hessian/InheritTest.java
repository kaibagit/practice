package com.luliru.test.hessian;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.luliru.test.hessian.bean.Son;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by luliru on 2017/7/18.
 */
public class InheritTest {

    public static void main(String[] args) throws IOException {
        FileOutputStream os = new FileOutputStream("son.txt");
        HessianOutput ho = new HessianOutput(os);
        ho.writeObject(new Son());

        FileInputStream is = new FileInputStream("son.txt");
        HessianInput hi = new HessianInput(is);
        Son son = (Son) hi.readObject();
        System.out.println(son.getName());  //father
    }
}
