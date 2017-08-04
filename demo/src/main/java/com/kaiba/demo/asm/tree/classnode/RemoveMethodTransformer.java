package com.kaiba.demo.asm.tree.classnode;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.Iterator;

/**
 * Created by luliru on 2017/7/23.
 */
public class RemoveMethodTransformer{
    private String methodName;
    private String methodDesc;
    public RemoveMethodTransformer(String methodName, String methodDesc) {
        this.methodName = methodName;
        this.methodDesc = methodDesc;
    }
    public void transform(ClassNode cn) {
        Iterator<MethodNode> i = cn.methods.iterator();
        while (i.hasNext()) {
            MethodNode mn = i.next();
            if (methodName.equals(mn.name) && methodDesc.equals(mn.desc)) {
                i.remove();
            }
        }
    }
}
