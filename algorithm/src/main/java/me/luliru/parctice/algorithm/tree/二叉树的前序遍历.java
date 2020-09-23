package me.luliru.parctice.algorithm.tree;

import java.util.LinkedList;
import java.util.List;

public class 二叉树的前序遍历 {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();

        if(root == null) {
            return result;
        }
        stack.addLast(root);

        while(!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            result.add(node.val);
            if(node.right != null) {
                stack.addLast(node.right);
            }
            if(node.left != null) {
                stack.addLast(node.left);
            }
        }
        return result;
    }
}
