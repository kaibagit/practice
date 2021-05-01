package me.luliru.parctice.algorithm.tree;

import java.util.LinkedList;

/**
 * LC173_二叉搜索树迭代器
 * Created by luliru on 2021/3/28.
 */
public class LC173_二叉搜索树迭代器 {
}

/**
 * 栈，模拟中序遍历
 */
class BSTIterator_210328_v1 {

    private LinkedList<TreeNode> stack = new LinkedList<>();

    public BSTIterator_210328_v1(TreeNode root) {
        TreeNode node = root;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public int next() {
        TreeNode top = stack.pop();
        TreeNode node = top.right;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        return top.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
