package me.luliru.parctice.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的镜像
 * Created by luliru on 2020/10/13.
 */
public class LC226_翻转二叉树 {

    /**
     * BFS
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode left = node.left;
            TreeNode right = node.right;
            node.left = right;
            node.right = left;

            if (left != null) {
                queue.offer(left);
            }
            if (right != null) {
                queue.offer(right);
            }
        }

        return root;
    }

    /**
     * 递归dfs
     * @param root
     * @return
     */
    public TreeNode invertTree_dfs(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode newLeftTree = invertTree_dfs(root.left);
        TreeNode newRightTree = invertTree_dfs(root.right);

        root.left = newRightTree;
        root.right = newLeftTree;

        return root;
    }












































    /**
     * BFS
     * @param root
     * @return
     */
    public TreeNode invertTree_210309_V2(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode left = node.left;
            TreeNode right = node.right;
            node.left = right;
            node.right = left;
            if (left != null) {
                queue.offer(left);
            }
            if (right != null) {
                queue.offer(right);
            }
        }

        return root;
    }
}
