package me.luliru.parctice.algorithm.tree;

/**
 * 剑指Offer33二叉搜索树的后序遍历序列
 * Created by luliru on 2020/10/13.
 */
public class 剑指Offer33二叉搜索树的后序遍历序列 {

    public boolean verifyPostorder(int[] postorder) {
        return isBst(postorder,0,postorder.length-1);
    }

    private boolean isBst(int[] arr,int left,int right) {
        // 空子树
        if (left >= right) {
            return true;
        }

        int root = arr[right];
        int p = left;
        // 找到左右子树分界
        while (arr[p] < root) {
            p++;
        }
        int m = p;
        while (arr[p] > root) {
            p++;
        }
        return p == right && isBst(arr,left,m-1) && isBst(arr,m,right);
    }
}
