package me.luliru.parctice.algorithm.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * LC337_打家劫舍III
 * Created by luliru on 2021/3/28.
 */
public class LC337_打家劫舍III {

    public static void main(String[] args) {
//        new LC337_打家劫舍III().rob()
    }

    /**
     * 递归
     * @param root
     * @return
     */
    public int rob_210328_v1(TreeNode root) {
        return dfs_v1(root);
    }

    /**
     * 递归
     * @param node
     * @return  最大能偷到的金额
     */
    private int dfs_v1(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 1、自己选择偷
        // 自己的金额 + 4个孙子节点能偷到的最大金额
        int choose = node.val;
        if (node.left != null) {
            choose += dfs_v1(node.left.left) + dfs_v1(node.left.right);
        }
        if (node.right != null) {
            choose += dfs_v1(node.right.left) + dfs_v1(node.right.right);
        }

        // 2、自己选择不偷
        int notChoose = dfs_v1(node.left) + dfs_v1(node.right);

        return Math.max(choose, notChoose);
    }

    /**
     * 递归 + 记忆化搜索
     * @param root
     * @return
     */
    public int rob_210328_v2(TreeNode root) {
        Map<TreeNode, Integer> mem = new HashMap<>();
        return dfs_v2(root, mem);
    }

    private int dfs_v2(TreeNode node, Map<TreeNode, Integer> mem) {
        if (node == null) {
            return 0;
        }

        if (mem.containsKey(node)) {
            return mem.get(node);
        }

        // 1、自己选择偷
        // 自己的金额 + 4个孙子节点能偷到的最大金额
        int choose = node.val;
        if (node.left != null) {
            choose += dfs_v2(node.left.left, mem) + dfs_v2(node.left.right, mem);
        }
        if (node.right != null) {
            choose += dfs_v2(node.right.left, mem) + dfs_v2(node.right.right, mem);
        }

        // 2、自己选择不偷
        int notChoose = dfs_v2(node.left, mem) + dfs_v2(node.right, mem);

        int money = Math.max(choose, notChoose);
        mem.put(node, money);

        return money;
    }

    /**
     * 递归 + 动态规划
     * @param root
     * @return
     */
    public int rob_210328_v3(TreeNode root) {
        Map<TreeNode, Integer> mem = new HashMap<>();
        return dfs_v3(root)[1];
    }

    /**
     * 递归
     * @param node
     * @return [不偷, 偷或不偷]
     */
    private int[] dfs_v3(TreeNode node) {
        if (node == null) {
            return new int[2];
        }

        int[] leftMoney = dfs_v3(node.left);
        int[] rightMoney = dfs_v3(node.right);

        // 1、自己选择不偷
        int notChoose = leftMoney[1] + rightMoney[1];

        // 2、自己选择偷
        // 自己的金额 + 2个子节点选择不偷
        int choose = node.val + leftMoney[0] + rightMoney[0];

        return new int[]{notChoose, Math.max(notChoose, choose)};
    }
}
