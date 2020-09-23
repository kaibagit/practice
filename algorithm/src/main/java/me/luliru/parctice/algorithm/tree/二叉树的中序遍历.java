package me.luliru.parctice.algorithm.tree;

import java.util.LinkedList;
import java.util.List;

public class 二叉树的中序遍历 {

    public List<Integer> postorderTraversal_V2(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();

        if(root == null) {
            return result;
        }

        TreeNode node = root;
        while(node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.addLast(node);
                node = node.left;
            }
            node = stack.pollLast();
            result.add(node.val);
            node = node.right;
        }

        return result;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();

        if(root == null) {
            return result;
        }
        stack.addLast(root);

        while(!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            if(node.right != null) {
                stack.addLast(node.right);
                node.right = null;
            }
            TreeNode left = node.left;
            if(left == null){
                result.add(node.val);
            }else {
                node.left = null;
                stack.addLast(node);
                stack.add(left);
            }
        }
        return result;
    }
}
