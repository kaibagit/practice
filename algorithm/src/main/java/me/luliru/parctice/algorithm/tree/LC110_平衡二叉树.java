package me.luliru.parctice.algorithm.tree;

/**
 * 剑指Offer55II平衡二叉树
 * Created by luliru on 2020/10/28.
 */
public class LC110_平衡二叉树 {

    public static void main(String[] args) {
        TreeNode root = TreeHelper.array2Tree(new Integer[]{1,2,2,3,3,null,null,4,4});
        boolean r = new LC110_平衡二叉树().isBalanced_OLD(root);
        System.out.println(r);
    }




    public boolean isBalanced(TreeNode root) {
        if(root == null) {
            return true;
        }

        Integer depth = dfs(root);

        return depth == null ? false : true;
    }

    /**
     * 获取数的深度
     * @param node
     * @return 当返回null时，表示不平衡
     */
    private Integer dfs(TreeNode node) {
        int leftDepth = 0, rightDepth = 0;
        if (node.left != null) {
            Integer leftResult = dfs(node.left);
            if (leftResult == null) {
                return null;
            } else {
                leftDepth = leftResult;
            }
        }
        if (node.right != null) {
            Integer rightResult = dfs(node.right);
            if (rightResult == null) {
                return null;
            } else {
                rightDepth = rightResult;
            }
        }

        if (leftDepth - rightDepth > 1 || rightDepth - leftDepth > 1) {
            return null;
        }

        return Math.max(leftDepth, rightDepth) + 1;
    }






































    public boolean isBalanced_OLD(TreeNode root) {
        return dfs_old(root)[0] == 1;
    }

    /**
     * 返回[是否平衡,深度]
     * @param node
     */
    private int[] dfs_old(TreeNode node) {
        if (node == null) {
            return new int[]{1,0};
        }

        int[] leftRes = dfs_old(node.left);
        int[] rightRes = dfs_old(node.right);
        if (leftRes[0] == 0 || rightRes[0] == 0) {
            return new int[]{0,0};
        }

        if (Math.abs(leftRes[1] - rightRes[1]) > 1) {
            return new int[]{0,0};
        }

        return new int[]{1, Math.max(leftRes[1],rightRes[1]) + 1};
    }
}
