package me.luliru.parctice.algorithm.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 路径总和II
 * Created by luliru on 2020/9/26.
 */
public class 路径总和II {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
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
