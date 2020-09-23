package me.luliru.parctice.algorithm.dp;

public class 打家劫舍III {

    public int rob(TreeNode root) {
        return  Math.max(loopv2(root,true),loopv2(root,false));
    }

    public int loop(TreeNode node,int sumOfLastSelect,int sumOfLastNotSelect) {
        if(node == null) {
            return Math.max(sumOfLastSelect,sumOfLastNotSelect);
        }
        int sumOfSelect = node.val + sumOfLastNotSelect;
        int sumOfNotSelect = sumOfLastSelect;
        return Math.max(loop(node.left,sumOfSelect,sumOfNotSelect),loop(node.right,sumOfSelect,sumOfNotSelect));
    }

    public int loopv2(TreeNode node,boolean choose) {
        if(node == null) {
            return 0;
        }
        if(choose) {
            return node.val + loopv2(node.left,false) + loopv2(node.right,false);
        }else {
            return loopv2(node.left,true) + loopv2(node.right,true);
        }
    }
}
