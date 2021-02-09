package me.luliru.parctice.algorithm.tree;

/**
 * 剑指Offer26树的子结构
 * Created by luliru on 2020/10/27.
 */
public class 剑指Offer26树的子结构 {

    public static void main(String[] args) {
        TreeNode A = TreeHelper.array2Tree(new Integer[]{1,2,3,4});
        TreeNode B = TreeHelper.array2Tree(new Integer[]{3});
//        TreeNode A = TreeHelper.array2Tree(new int[]{4,2,3,4,5,6,7,8,9});
//        TreeNode B = TreeHelper.array2Tree(new int[]{4,8,9});
        System.out.println(new 剑指Offer26树的子结构().isSubStructure(A,B));
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null) {
            return false;
        }

        return dfs(A,B);
    }

    private boolean dfs(TreeNode A,TreeNode B) {
        if (A == null) {
            return false;
        }
        return isSubTree(A,B)
                || dfs(A.left,B)
                || dfs(A.right,B);
    }

    /**
     *
     * @param parent
     * @param target
     * @return
     */
    private boolean isSubTree(TreeNode parent,TreeNode target) {
        if (target == null) {
            return true;
        }

        if (parent == null) {
            return false;
        }

        return parent.val == target.val
                && isSubTree(parent.left,target.left)
                && isSubTree(parent.right,target.right);
    }
}
