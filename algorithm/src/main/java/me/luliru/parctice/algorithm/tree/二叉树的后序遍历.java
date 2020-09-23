package me.luliru.parctice.algorithm.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class 二叉树的后序遍历 {

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

    public List<Integer> postorderTraversal(TreeNode root) {
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
