package me.luliru.parctice.algorithm.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class LC145_二叉树的后序遍历 {


    /**
     * 栈，非递归
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> output = new LinkedList<>();

        if (root == null) {
            return output;
        }

        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode head = stack.pop();
            output.addFirst(head.val);
            if (head.left != null) {
                stack.push(head.left);
            }
            if (head.right != null) {
                stack.push(head.right);
            }
        }

        return output;
    }

    /**
     * 递归
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal_recurse(TreeNode root) {
        List<Integer> output = new LinkedList<>();
        postorder(root, output);
        return output;
    }
    private void postorder(TreeNode head, List<Integer> output) {
        if (head == null) {
            return;
        }

        postorder(head.left, output);
        postorder(head.right, output);
        output.add(head.val);
    }



















    public List<Integer> postorderTraversal_V2(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
        TreeNode node = root;
        TreeNode lastVisit = root;
        while (node != null || !treeNodeStack.isEmpty()) {
            while (node != null) {
                treeNodeStack.push(node);
                node = node.left;
            }
            //查看当前栈顶元素
            node = treeNodeStack.peek();
            //如果其右子树也为空，或者右子树已经访问
            // 则可以直接输出当前节点的值
            if (node.right == null || node.right == lastVisit) {
                result.add(node.val);
                treeNodeStack.pop();
                lastVisit = node;
                node = null;
            } else {
                //否则，继续遍历右子树
                node = node.right;
            }
        }
        return result;
    }

    public List<Integer> postorderTraversal_old(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();

        TreeNode node = root;
        TreeNode p = null;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.addLast(node);
                node = node.left;
            }
            node = stack.peek();
            if(node.right == null || node.right == p) {
                stack.pop();
                result.add(node.val);
                p = node;
                node = null;
            } else {
                node = node.right;
            }
        }

        return result;
    }
}
