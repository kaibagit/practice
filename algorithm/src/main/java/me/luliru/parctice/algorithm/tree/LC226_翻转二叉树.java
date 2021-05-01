package me.luliru.parctice.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的镜像
 * Created by luliru on 2020/10/13.
 */
public class LC226_翻转二叉树 {


    /**
     * dfs
     * @param root
     * @return
     */
    public TreeNode invertTree_210309_V1(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = invertTree_210309_V1(right);
        root.right = invertTree_210309_V1(left);

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








































    public TreeNode mirrorTree_old(TreeNode root) {
        return reverse(root);
    }

    private TreeNode reverse(TreeNode node) {
        if(node == null) {
            return null;
        }
        TreeNode tmp = reverse(node.left);
        node.left = reverse(node.right);
        node.right = tmp;
        return node;
    }
}
