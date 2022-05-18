package me.luliru.parctice.algorithm.tree;

public class 二叉树总结 {

    public void f(TreeNode head) {
        if (head == null) {
            return;
        }

        // 1、先序
        f(head.left);
        // 2、中序
        f(head.right);
        // 3、后序
    }
}
