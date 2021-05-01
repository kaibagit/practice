package me.luliru.parctice.algorithm.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 路径总和II
 * Created by luliru on 2020/9/26.
 */
public class LC113_路径总和II {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        dfs(ans, root, new ArrayList<>(), targetSum);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, TreeNode root, List<Integer> path, int sum) {
        // 已经到达叶子节点
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                List<Integer> ret = new ArrayList<>(path);
                ret.add(root.val);
                ans.add(ret);
            }
            return;
        }

        // 选择
        path.add(root.val);
        int nextSum = sum - root.val;

        if (root.left != null) {
            // 进入左子树
            dfs(ans, root.left, path, nextSum);
        }
        if (root.right != null) {
            // 进入右子树
            dfs(ans, root.right, path, nextSum);
        }

        // 撤销选择
        path.remove(path.size() - 1);
    }












































    public List<List<Integer>> pathSum_old(TreeNode root, int sum) {
        return loop(root,sum,new ArrayList<>());
    }

    public List<List<Integer>> loop(TreeNode node, int leftSum,List<Integer> path) {
        if(node == null) {
            return Collections.emptyList();
        }

        int subSum = leftSum - node.val;
        List<Integer> currPath = new ArrayList<>();
        currPath.addAll(path);
        currPath.add(node.val);
        if(node.left == null && node.right == null) {
            if(subSum == 0) {
                List<List<Integer>> result = new ArrayList<>();
                result.add(currPath);
                return result;
            } else {
                return Collections.emptyList();
            }
        }

        List<List<Integer>> leftResult = loop(node.left,subSum,currPath);
        List<List<Integer>> rightResult = loop(node.right,subSum,currPath);
        return merge(leftResult,rightResult);
    }

    public List<List<Integer>> merge(List<List<Integer>> leftResult,List<List<Integer>> rightResult) {
        if(leftResult.isEmpty()) {
            return rightResult;
        }
        if(rightResult.isEmpty()) {
            return leftResult;
        }
        leftResult.addAll(rightResult);
        return leftResult;
    }
}
