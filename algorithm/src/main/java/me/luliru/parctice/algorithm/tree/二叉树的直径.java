//package me.luliru.parctice.algorithm.tree;
//
///**
// * 二叉树的直径
// * Created by luliru on 2020/10/18.
// */
//public class 二叉树的直径 {
//
//    private int ans = 0;
//
//    public int diameterOfBinaryTree(TreeNode root) {
//        dfs(root);
//        return ans;
//    }
//
//    private int dfs(TreeNode node) {
//        if(node == null) {
//            return 0;
//        }
//        int leftDepth = dfs(node.left);
//        int rightDepth = dfs(node.right);
//        ans = Math.max(leftDepth+rightDepth,ans);
//        return Math.max(leftDepth,rightDepth)+1;
//    }
//}
