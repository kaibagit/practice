package me.luliru.parctice.algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指Offer32II从上到下打印二叉树II
 * Created by luliru on 2020/10/27.
 */
public class 剑指Offer32II从上到下打印二叉树II {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelCount = queue.size();
            List<Integer> level = new ArrayList<>();
            res.add(level);
            for (int i = 1; i <= levelCount; i++) {
                TreeNode node = queue.pollFirst();
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
                level.add(node.val);
            }
        }

        return res;
    }
}
