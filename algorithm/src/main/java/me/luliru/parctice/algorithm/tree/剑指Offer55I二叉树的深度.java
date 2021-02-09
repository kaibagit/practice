package me.luliru.parctice.algorithm.tree;

/**
 * 剑指Offer55I二叉树的深度
 * Created by luliru on 2020/10/28.
 */
public class 剑指Offer55I二叉树的深度 {

    public int maxDepth(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftDepth = dfs(node.left);
        int rightDepth = dfs(node.right);
        return Math.max(leftDepth,rightDepth) + 1;
    }
}
