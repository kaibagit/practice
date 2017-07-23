package com.kaiba.demo.asm.tree;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

/**
 * Created by luliru on 2017/7/23.
 */
public class AddFieldTransformer {
    private int fieldAccess;
    private String fieldName;
    private String fieldDesc;
    public AddFieldTransformer(int fieldAccess,
                               String fieldName, String fieldDesc) {
        this.fieldAccess = fieldAccess;
        this.fieldName = fieldName;
        this.fieldDesc = fieldDesc;
    }

    public void transform(ClassNode cn) {
        boolean isPresent = false;
        for (Object obj : cn.fields) {
            FieldNode fn = (FieldNode)obj;
            if (fieldName.equals(fn.name)) {
                isPresent = true;
                break;
            }
        }
        if (!isPresent) {
            cn.fields.add(new FieldNode(fieldAccess, fieldName, fieldDesc,
                    null, null));
        }
    }
}
