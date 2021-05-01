package me.luliru.parctice.algorithm.tree;

import java.util.LinkedList;

/**
 * 二叉树的最小深度
 * Created by luliru on 2021/2/13.
 */
public class 二叉树的最小深度 {

    public static void main(String[] args) {
        TreeNode root = TreeHelper.array2Tree(new Integer[]{2,null,3,null,4,null,5,null,6});
        new 二叉树的最小深度().minDepth_dfs(root);
    }

    /**
     * 深度优先
     * @param root
     * @return
     */
    public int minDepth_dfs(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        // 执行到这里，左右必有节点
        if (node.left == null) {
            return dfs(node.right) + 1;
        }
        if (node.right == null) {
            return dfs(node.left) + 1;
        }
        // 执行到这里，左右都非空节点
        int left = dfs(node.left);
        int right = dfs(node.right);
        return Math.min(left, right) + 1;
    }

    /**
     * 广度优先
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        int depth = 1;
        int levelCount = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            for (int i = 0; i < levelCount; i++) {
                TreeNode node = queue.pop();
                if (node.left == null && node.right == null) {
                    return depth;
                }
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
        return depth - 1;   // 之前while循环depth++多执行了一次，需要扣除
    }
}
