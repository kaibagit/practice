package me.luliru.parctice.algorithm.tree;

/**
 * 二叉搜索树的最近公共祖先
 * Created by luliru on 2021/2/12.
 */
public class 二叉搜索树的最近公共祖先 {

    /**
     * 循环
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
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

    /**
     * 递归
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor_v1(TreeNode root, TreeNode p, TreeNode q) {
        return findPorQ(root, p, q);
    }

    public TreeNode findPorQ(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return null;
        }
        if (node == p || node == q) {
            return node;
        }
        if (node.val > p.val && node.val > q.val) {     // 都比node小，说明在左子树上
            return findPorQ(node.left, p, q);
        }
        if (node.val < p.val && node.val < q.val) {     // 都比node大，说明在右子树上
            return findPorQ(node.right, p, q);
        }
        return node;    // 一个在左子树，一个在右子树上，则node就位公共祖先
    }
}
