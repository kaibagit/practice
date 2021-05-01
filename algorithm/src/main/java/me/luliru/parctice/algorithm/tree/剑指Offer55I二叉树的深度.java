package me.luliru.parctice.algorithm.tree;

import java.util.LinkedList;

/**
 * 剑指Offer55I二叉树的深度
 * Created by luliru on 2020/10/28.
 */
public class 剑指Offer55I二叉树的深度 {

    public static void main(String[] args) {
        TreeNode root = TreeHelper.array2Tree(new Integer[]{1,2,3,4,null,null,5});
        new 剑指Offer55I二叉树的深度().maxDepth(root);
    }

    /**
     * 广度优先
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        int depth = 0;
        int levelCount = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            for (int i = 0; i < levelCount; i++) {
                TreeNode node = queue.pop();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            depth++;
            levelCount = queue.size();
        }
        return depth;
    }



    /**
     * 深度优先+递归
     * @param root
     * @return
     */
    public int maxDepth_dfs(TreeNode root) {
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
