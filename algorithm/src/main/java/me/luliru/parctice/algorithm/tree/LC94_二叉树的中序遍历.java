package me.luliru.parctice.algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC94_二叉树的中序遍历 {

    /**
     * 栈，左边界分割
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> output = new LinkedList<>();

        if (root == null) {
            return output;
        }

        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {  //退出条件：node == null
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            output.add(node.val);
            node = node.right;
        }


        return output;
    }

    /**
     * 递归
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal_recurse(TreeNode root) {
        List<Integer> output = new LinkedList<>();

        inorder(root, output);

        return output;
    }
    private void inorder(TreeNode head, List<Integer> output) {
        if (head == null) {
            return;
        }

        inorder(head.left, output);
        output.add(head.val);
        inorder(head.right, output);
    }

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

    public List<Integer> inorderTraversal_old(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode node = root;
        TreeNode predecessor = null;

        while (node != null) {
            if (node.left == null) { // 如果没有左孩子，则直接访问右孩子
                res.add(node.val);
                node = node.right;
            } else {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor = node.left;
                while (predecessor.right != null && predecessor.right != node) {
                    predecessor = predecessor.right;
                }

                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = node;
                    node = node.left;
                } else { // 说明左子树已经访问完了，我们需要断开链接
                    res.add(node.val);
                    predecessor.right = null;
                    node = node.right;
                }
            }
        }
        return res;
    }
}
