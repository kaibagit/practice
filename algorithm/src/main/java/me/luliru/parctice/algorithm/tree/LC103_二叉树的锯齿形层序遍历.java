package me.luliru.parctice.algorithm.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * LC103_二叉树的锯齿形层序遍历
 * Created by luliru on 2021/3/1.
 */
public class LC103_二叉树的锯齿形层序遍历 {

    /**
     * BFS
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder_210301(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if (root == null) {
            return ans;
        }

        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        int levelCount = 1;
        boolean positive = true;
        while (!deque.isEmpty()) {
            List<Integer> level = new ArrayList<>(levelCount);
            for (int i = 0; i < levelCount; i++) {
                // 弹出元素，加入列表
                if (positive) {
                    TreeNode node = deque.pollFirst();
                    level.add(node.val);
                    if (node.left != null) {
                        deque.addLast(node.left);
                    }
                    if (node.right != null) {
                        deque.addLast(node.right);
                    }
                } else {
                    TreeNode node = deque.pollLast();
                    level.add(node.val);
                    if (node.right != null) {
                        deque.addFirst(node.right);
                    }
                    if (node.left != null) {
                        deque.addFirst(node.left);
                    }
                }
            }
            ans.add(level);

            // 继续下一层
            levelCount = deque.size();
            positive = !positive;
        }

        return ans;
    }

    /**
     * DFS
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if (root == null) {
            return ans;
        }

        dfs(ans, 0, root);

        return ans;
    }

    private void dfs(List<List<Integer>> ans, int level, TreeNode node) {
        // 1、创建层级
        LinkedList<Integer> levelList = null;
        if (level >= ans.size()) {
            levelList = new LinkedList<>();
            ans.add(levelList);
        } else {
            levelList = (LinkedList<Integer>) ans.get(level);
        }

        // 2、加入节点
        if ((level & 1) == 0) {     // 偶数
            levelList.addLast(node.val);
        } else {
            levelList.addFirst(node.val);
        }

        // 3、处理子节点
        if (node.left != null) {
            dfs(ans, level + 1, node.left);
        }
        if (node.right != null) {
            dfs(ans, level + 1, node.right);
        }
    }
}
