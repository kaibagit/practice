package me.luliru.parctice.algorithm.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指Offer33二叉搜索树的后序遍历序列
 * Created by luliru on 2020/10/13.
 */
public class 剑指Offer33_二叉搜索树的后序遍历序列 {

    public static void main(String[] args) {
        int[] postorder = new int[]{4, 8, 6, 12, 16, 14, 10};
        System.out.println(new 剑指Offer33_二叉搜索树的后序遍历序列().verifyPostorder_210309_v1(postorder));
    }

    /**
     * 递归
     * @param postorder
     * @return
     */
    public boolean verifyPostorder_210309_v1(int[] postorder) {
        return validate(postorder, 0, postorder.length - 1);
    }

    private boolean validate(int[] postorder, int left, int right) {
        if (left >= right) {
            return true;
        }
        int root = postorder[right];

        int index = left;      // 左子树范围[0,index)，右子树范围[index,n)
        while (index < right) {     // 退出条件：index指向第一个大于root的位置
            if (postorder[index] > root) {
                break;
            }
            ++index;
        }
        int mid = index - 1;    // 左子树的最后一个位置
        while (index < right) {     // 退出条件：index指向root的位置，或者是小于root的位置
            if (postorder[index] <= root) {
                break;
            }
            ++index;
        }

        // 要求右子树所有的值都要大于root，如果满足，最后index = right
        if (index != right) {
            return false;
        }

        boolean leftValidate = validate(postorder, left, mid);
        if (!leftValidate) {
            return false;
        }
        return validate(postorder, mid + 1, right - 1);
    }


    /**
     * 单调栈
     * @param postorder
     * @return
     */
    public boolean verifyPostorder_210309_v2(int[] postorder) {
        // 遍历数组的所有元素，如果栈为空，就把当前元素压栈。如果栈不为空，并且当前元素大于栈顶元素，说明是升序的，那么就说明当前元素是栈顶元素的右子节点，就把当前元素压栈，如果一直升序，就一直压栈。

        // Stack依次存入的root，右节点；如果碰到左节点，则将所有比它大的节点全部弹出
        LinkedList<Integer> rightAndRootStack = new LinkedList<>();
        int preRoot = Integer.MAX_VALUE;
        for (int i = postorder.length - 1;i>=0;i--){
            int curr = postorder[i];
            // 左子树上的元素，必然要小于之前根节点，否则就不是BST
            if (curr > preRoot){
                return false;
            }
            while (!rightAndRootStack.isEmpty() && curr < rightAndRootStack.peek()){
                // 最后一个pop的，就是curr的根节点
                preRoot = rightAndRootStack.pop();
            }
            rightAndRootStack.push(curr);
        }
        return true;
    }










































    public boolean verifyPostorder_old(int[] postorder) {
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
