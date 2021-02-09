package me.luliru.parctice.algorithm.tree;

/**
 * 剑指Offer55II平衡二叉树
 * Created by luliru on 2020/10/28.
 */
public class 剑指Offer55II平衡二叉树 {

    public static void main(String[] args) {
        TreeNode root = TreeHelper.array2Tree(new Integer[]{1,2,2,3,3,null,null,4,4});
        boolean r = new 剑指Offer55II平衡二叉树().isBalanced(root);
        System.out.println(r);
    }

    public boolean isBalanced(TreeNode root) {
        return dfs(root)[0] == 1;
    }

    /**
     * 返回[是否平衡,深度]
     * @param node
     */
    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{1,0};
        }

        int[] leftRes = dfs(node.left);
        int[] rightRes = dfs(node.right);
        if (leftRes[0] == 0 || rightRes[0] == 0) {
            return new int[]{0,0};
        }

        if (Math.abs(leftRes[1] - rightRes[1]) > 1) {
            return new int[]{0,0};
        }

        return new int[]{1, Math.max(leftRes[1],rightRes[1]) + 1};
    }
}
