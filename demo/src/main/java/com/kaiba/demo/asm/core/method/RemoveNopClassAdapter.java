package com.kaiba.demo.asm.core.method;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM5;

/**
 * Created by luliru on 2017/7/25.
 */
public class RemoveNopClassAdapter extends ClassVisitor {

    public RemoveNopClassAdapter(ClassVisitor cv) {
        super(ASM5, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name,
                                     String desc, String signature, String[] exceptions) {
        MethodVisitor mv;
        mv = cv.visitMethod(access, name, desc, signature, exceptions);
        if (mv != null) {
            mv = new RemoveNopMethodAdapter(mv);
        }
        return mv;
    }
}
