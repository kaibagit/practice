package me.luliru.parctice.algorithm.tree;

import java.util.LinkedList;

public class 面试题04_06_后继者 {

    /**
     * 利用BST特性
     * https://leetcode.cn/problems/successor-lcci/solution/by-ac_oier-xib5/
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        }

        // 若有 root.val > p.val : 当第一次搜索到满足此条件的节点时，在以 root 为根节点的子树中「位于最左下方」的值为 p 的后继，但也有可能 root 没有左子树，因此 p 的后继要么在 root 的左子树中（若有），要么是 root 本身
        TreeNode node = inorderSuccessor(root.left, p);
        return node == null ? root : node;
    }

    /**
     * 栈+中序遍历+前置节点
     * https://leetcode.cn/problems/successor-lcci/solution/hou-ji-zhe-by-leetcode-solution-6hgc/
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor_stack(TreeNode root, TreeNode p) {
        LinkedList<TreeNode> stack = new LinkedList<>();

        TreeNode pre = null;
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            if (pre != null && pre.val == p.val) {
                return node;
            }
            pre = node;
            node = node.right;
        }

        return null;
    }


}
