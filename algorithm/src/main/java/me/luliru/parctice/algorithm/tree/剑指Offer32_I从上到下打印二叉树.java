package me.luliru.parctice.algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指Offer32_I从上到下打印二叉树
 * Created by luliru on 2020/10/14.
 */
public class 剑指Offer32_I从上到下打印二叉树 {

    public int[] levelOrder(TreeNode root) {
        if(root == null) {
            return new int[]{};
        }
        List<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        res.add(root.val);
        if(root.left != null) {
            queue.addLast(root.left);
        }
        if(root.right != null) {
            queue.addLast(root.right);
        }
        while (!queue.isEmpty()) {
            TreeNode node = queue.pollFirst();
            res.add(node.val);
            if(node.left != null) {
                queue.addLast(node.left);
            }
            if(node.right != null) {
                queue.addLast(node.right);
            }
        }
        int[] intRes = new int[res.size()];
        for(int i=0;i<res.size();i++) {
            intRes[i] = res.get(i);
        }
        return intRes;
    }
}
