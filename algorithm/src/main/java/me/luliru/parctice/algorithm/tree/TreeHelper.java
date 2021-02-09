package me.luliru.parctice.algorithm.tree;

/**
 * TreeHelper
 * Created by luliru on 2020/10/27.
 */
public class TreeHelper {

    public static TreeNode array2Tree(Integer[] arr) {
        TreeNode[] nodeArr = new TreeNode[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                continue;
            }
            TreeNode node = new TreeNode(arr[i]);
            nodeArr[i] = node;

            if(i > 0) {
                TreeNode parent = nodeArr[(i-1)/2];
                // 奇数在左子树
                if (i % 2 == 1) {
                    parent.left = node;
                } else {    // 偶数在由子树
                    parent.right = node;
                }
            }
        }
        return nodeArr[0];
    }
}
