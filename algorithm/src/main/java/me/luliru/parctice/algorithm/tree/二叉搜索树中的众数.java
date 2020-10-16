package me.luliru.parctice.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树中的众数
 * Created by luliru on 2020/9/25.
 */
public class 二叉搜索树中的众数 {

    private int base,count,maxCount;
    private List<Integer> result = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        TreeNode predecessor = null;

        while (root != null) {
            if (root.left != null) {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }

                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                } else { // 说明左子树已经访问完了，我们需要断开链接
                    update(root.val);
                    predecessor.right = null;
                    root = root.right;
                }
            } else { // 如果没有左孩子，则直接访问右孩子
                update(root.val);
                root = root.right;
            }
        }
        int[] ans = new int[result.size()];
        for(int i=0;i<result.size();i++) {
            ans[i] = result.get(i);
        }
        return ans;
    }

    public void update(int x) {
        if(x != base) {
            base = x;
            count = 1;
        } else {
            count++;
        }
        if(count == maxCount) {
            result.add(x);
        }
        if(count > maxCount) {
            maxCount = count;
            result.clear();
            result.add(x);
        }
    }
}
