package me.luliru.parctice.algorithm.tree;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC144_二叉树的前序遍历 {

    /**
     * 栈，非递归
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> output = new LinkedList<>();

        if (root == null) {
            return output;
        }

        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode head = stack.pop();
            output.add(head.val);
            if (head.right != null) {
                stack.push(head.right);
            }
            if (head.left != null) {
                stack.push(head.left);
            }
        }

        return output;
    }

    /**
     * 递归
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal_recurse(TreeNode root) {
        List<Integer> output = new LinkedList<>();
        preorder(root, output);
        return output;
    }
    private void preorder(TreeNode head, List<Integer> output) {
        if (head == null) {
            return;
        }

        output.add(head.val);
        preorder(head.left, output);
        preorder(head.right, output);
    }



























    public List<Integer> preorderTraversal_old(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            if(node.right != null) {
                stack.push(node.right);
            }
            if(node.left != null) {
                stack.push(node.left);
            }
        }

        return res;
    }
}
