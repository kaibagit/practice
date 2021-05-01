package me.luliru.parctice.algorithm.tree;

import java.util.LinkedList;

/**
 * 验证二叉搜索树
 * Created by luliru on 2021/2/12.
 */
public class 验证二叉搜索树 {

    /**
     * 中序遍历
     * @param root
     * @return
     */
    public boolean isValidBST_v1(TreeNode root) {
        // 中序遍历要求输出结果是从小到大排列的
        Integer minBound = null;
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            // 输出node
            node = stack.pop();
            if (minBound != null && minBound >= node.val) {
                return false;
            } else {
                minBound = node.val;
            }
            node = node.right;
        }
        return true;
    }

    /**
     * 递归
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isBst(root, null, null);
    }

    public boolean isBst(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }
        if (lower != null && node.val <= lower) {
            return false;
        }
        if (upper != null && node.val >= upper) {
            return false;
        }
        return isBst(node.left, lower, node.val) && isBst(node.right, node.val, upper);
    }
}
