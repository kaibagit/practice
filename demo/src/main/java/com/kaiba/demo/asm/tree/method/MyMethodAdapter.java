package com.kaiba.demo.asm.tree.method;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.MethodNode;

import static org.objectweb.asm.Opcodes.ASM5;

/**
 * Created by luliru on 2017/7/25.
 */
public class MyMethodAdapter extends MethodNode {

    public MyMethodAdapter(int access, String name, String desc,
                           String signature, String[] exceptions, MethodVisitor mv) {
        super(ASM5, access, name, desc, signature, exceptions);
        this.mv = mv;
    }
    @Override public void visitEnd() {
        // put your transformation code here
        accept(mv);
    }
}
