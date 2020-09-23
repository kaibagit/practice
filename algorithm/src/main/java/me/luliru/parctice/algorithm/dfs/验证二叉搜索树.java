package me.luliru.parctice.algorithm.dfs;

public class 验证二叉搜索树 {

    public boolean isValidBST(TreeNode root) {
        if(root == null) {
            return true;
        }
        return check(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    public boolean check(TreeNode node, int min, int max) {
        if(node == null) {
            return true;
        }
        if(node.left != null && node.val < node.left.val) {
            return false;
        }
        if(node.right != null && node.val > node.right.val) {
            return false;
        }
        if(node.val <= min || node.val >= max) {
            return false;
        }
        int leftMin = min;
        int leftMax = node.val;
        int rightMin = node.val;
        int rightMax = max;

        return check(node.left,leftMin,leftMax)
                && check(node.right,rightMin,rightMax);
    }
}
