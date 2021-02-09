package me.luliru.parctice.algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指Offer32III从上到下打印二叉树III
 * Created by luliru on 2020/10/28.
 */
public class 剑指Offer32III从上到下打印二叉树III {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);

        boolean even = false;

        while (!queue.isEmpty()) {
            LinkedList<Integer> level = new LinkedList<>();
            res.add(level);
            int levelCount = queue.size();

            for (int i = 1; i <= levelCount; i++) {
                TreeNode node = queue.pollFirst();
                if (even) {
                    level.addFirst(node.val);
                } else {
                    level.addLast(node.val);
                }
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
            even = !even;
        }

        return res;
    }
}
