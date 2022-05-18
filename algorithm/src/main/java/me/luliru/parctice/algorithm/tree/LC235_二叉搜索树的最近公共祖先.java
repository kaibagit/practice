package me.luliru.parctice.algorithm.tree;

/**
 * 二叉搜索树的最近公共祖先
 * Created by luliru on 2021/2/12.
 */
public class LC235_二叉搜索树的最近公共祖先 {


    /**
     * 递归
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        return root;
    }








































    /**
     * 循环
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor_old(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while (node != null) {
            if (node == p || node == q) {
                return node;
            }
            if (node.val < p.val && node.val < q.val) {
                node = node.right;
                continue;
            }
            if (node.val > p.val && node.val > q.val) {
                node = node.left;
                continue;
            }
            return node;
        }

        return null;
    }
}
