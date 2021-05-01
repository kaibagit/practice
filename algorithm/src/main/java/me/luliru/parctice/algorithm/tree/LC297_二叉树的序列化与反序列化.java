package me.luliru.parctice.algorithm.tree;

import java.util.LinkedList;

/**
 * 剑指Offer37序列化二叉树
 * Created by luliru on 2020/10/28.
 */
public class LC297_二叉树的序列化与反序列化 {

    public static void main(String[] args) {
        TreeNode root = TreeHelper.array2Tree(new Integer[]{1});
    }
}

/**
 * DFS + 先序遍历
 */
class Codec_210310_v1 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "N";
        }

        StringBuilder data = new StringBuilder();
        dfs(root, data);
        data.deleteCharAt(data.length() - 1);

        return data.toString();
    }

    /**
     * 递归序列化，先序遍历
     * @param node
     * @param data
     */
    private void dfs(TreeNode node, StringBuilder data) {
        data.append(node.val).append(",");

        if (node.left != null) {
            dfs(node.left, data);
        } else {
            data.append("N").append(",");
        }
        if (node.right != null) {
            dfs(node.right, data);
        } else {
            data.append("N").append(",");
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] valueArr = data.split(",");

        Object[] result = deDfs(valueArr, 0);

        return (TreeNode) result[0];
    }

    /**
     * 反序列化DFS
     * @param valueArr
     * @param start
     * @return arr[0]代表反序列化后的Node，arr[1]代表以该Node为根的子树的节点个数（含null）
     */
    private Object[] deDfs(String[] valueArr, int start) {
        if (valueArr[start].equals("N")) {
            return new Object[]{null, 1};
        }

        int rootValue = Integer.valueOf(valueArr[start]);
        TreeNode root = new TreeNode(rootValue);
        Object[] leftResult = deDfs(valueArr, start + 1);
        Object[] rightResult = deDfs(valueArr, start + 1 + (int)leftResult[1]);
        root.left = (TreeNode) leftResult[0];
        root.right = (TreeNode) rightResult[0];

        return new Object[]{root, 1 + (int)leftResult[1] + (int)rightResult[1]};
    }
}

/**
 * BFS
 */
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "N";
        }

        StringBuilder data = new StringBuilder();
        LinkedList<TreeNode> queue = new LinkedList<>();    // 子节点待序列化的Node队列

        data.append(root.val).append(",");
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                data.append(node.left.val).append(",");
                queue.offer(node.left);
            } else {
                data.append("N").append(",");
            }
            if (node.right != null) {
                data.append(node.right.val).append(",");
                queue.offer(node.right);
            } else {
                data.append("N").append(",");
            }
        }

        data.deleteCharAt(data.length() - 1);   // 移除最后一个逗号
        return data.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("N")) {
            return null;
        }

        String[] valueArr = data.split(",");

        LinkedList<TreeNode> parentQueue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(valueArr[0]));
        parentQueue.offer(root);
        int index = 1;
        while (!parentQueue.isEmpty()) {
            TreeNode parent = parentQueue.poll();
            String leftVal = valueArr[index];
            ++index;
            String rightVal = valueArr[index];
            ++index;

            if (!leftVal.equals("N")) {
                TreeNode left = new TreeNode(Integer.valueOf(leftVal));
                parent.left = left;
                parentQueue.offer(left);
            }

            if (!rightVal.equals("N")) {
                TreeNode right = new TreeNode(Integer.valueOf(rightVal));
                parent.right = right;
                parentQueue.offer(right);
            }
        }

        return root;
    }
}
