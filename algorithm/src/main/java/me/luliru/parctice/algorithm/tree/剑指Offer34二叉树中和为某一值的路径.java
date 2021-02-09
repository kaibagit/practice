package me.luliru.parctice.algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 剑指Offer34二叉树中和为某一值的路径
 * Created by luliru on 2020/10/28.
 */
public class 剑指Offer34二叉树中和为某一值的路径 {

    public static void main(String[] args) {
        TreeNode root = TreeHelper.array2Tree(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,5,1});
        new 剑指Offer34二叉树中和为某一值的路径().pathSum(root,22);
    }

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return res;
        }
        LinkedList<TreeNode> path = new LinkedList<>();
        backtrack(path,root,sum);
        return res;
    }

    private void backtrack(LinkedList<TreeNode> path,TreeNode node,int require) {
        if (node == null) {
            return;
        }
        // 是叶子节点，且符合条件
        if (node.left == null && node.right == null && node.val == require) {
            List<Integer> ans = path.stream()
                    .map(pathNode -> pathNode.val)
                    .collect(Collectors.toList());
            ans.add(node.val);
            res.add(ans);
            return;
        }

//        // 到达叶子节点，且值正好归零
//        if (node == null && require == 0) {
//            List<Integer> ans = path.stream()
//                    .map(pathNode -> pathNode.val)
//                    .collect(Collectors.toList());
//            res.add(ans);
//            return;
//        }

        // 做选择
        path.add(node);
        require -= node.val;

        // backtrack
        backtrack(path,node.left,require);
        backtrack(path,node.right,require);

        // 撤销选择
        path.pollLast();
    }
}
