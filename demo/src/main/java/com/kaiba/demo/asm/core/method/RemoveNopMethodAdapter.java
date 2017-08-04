package com.kaiba.demo.asm.core.method;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM5;
import static org.objectweb.asm.Opcodes.NOP;

/**
 * Created by luliru on 2017/7/25.
 */
public class RemoveNopMethodAdapter extends MethodVisitor {

    public RemoveNopMethodAdapter(MethodVisitor mv) {
        super(ASM5, mv);
    }

    @Override
    public void visitInsn(int opcode) {
        if (opcode != NOP) {
            mv.visitInsn(opcode);
        }
    }
}
