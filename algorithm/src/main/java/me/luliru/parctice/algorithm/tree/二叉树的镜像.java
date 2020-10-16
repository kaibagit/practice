package me.luliru.parctice.algorithm.tree;

/**
 * 二叉树的镜像
 * Created by luliru on 2020/10/13.
 */
public class 二叉树的镜像 {

    public TreeNode mirrorTree(TreeNode root) {
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
