package com.kaiba.demo.asm.tree.method;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.MethodNode;

import static org.objectweb.asm.Opcodes.ASM5;

/**
 * Created by luliru on 2017/7/25.
 */
public class MyMethodAdapter2 extends MethodVisitor {

    MethodVisitor next;

    public MyMethodAdapter2(int access, String name, String desc,
                           String signature, String[] exceptions, MethodVisitor mv) {
        super(ASM5,
                new MethodNode(access, name, desc, signature, exceptions));
        next = mv;
    }

    @Override
    public void visitEnd() {
        MethodNode mn = (MethodNode) mv;
        // put your transformation code here
        mn.accept(next);
    }
}
