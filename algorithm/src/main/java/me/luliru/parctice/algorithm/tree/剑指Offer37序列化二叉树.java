package me.luliru.parctice.algorithm.tree;

import java.util.LinkedList;

/**
 * 剑指Offer37序列化二叉树
 * Created by luliru on 2020/10/28.
 */
public class 剑指Offer37序列化二叉树 {

    public static void main(String[] args) {
        TreeNode root = TreeHelper.array2Tree(new Integer[]{1});
        Codec codec = new Codec();
        String data = codec.serialize(root);
        TreeNode root2 = codec.deserialize(data);
        System.out.println(root2);
    }

    static class Codec {

        private static String mark = ",";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder data = new StringBuilder();

            LinkedList<TreeNode> queue = new LinkedList<>();
            int levelCount = 0;     //该层的总元素个数
            int validCount = 0;     //队列中有效的节点个数，如果为0，则结束

            if (root != null) {
                queue.addLast(root);
                levelCount = 1;
                validCount = 1;
            }
            while (!queue.isEmpty() && validCount > 0) {
                // 该层开始，重置有效节点个数
                validCount = 0;

                for (int i = 0; i < levelCount; i++) {
                    TreeNode node = queue.pollFirst();

                    if (node != null) {
                        // 将当前节点序列化
                        if (data.length() > 0) {
                            data.append(mark);
                        }
                        data.append(String.valueOf(node.val));

                        // 将左右节点放入队列
                        queue.addLast(node.left);
                        queue.addLast(node.right);

                        if (node.left != null) {
                            validCount++;
                        }
                        if (node.right != null) {
                            validCount++;
                        }
                    } else {
                        if (data.length() > 0) {
                            data.append(mark);
                        }
                        data.append("null");

                        queue.addLast(null);
                        queue.addLast(null);
                    }
                }

                // 下一层开始
                levelCount = levelCount * 2;
            }

            return data.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.length() == 0) {
                return null;
            }

            String[] valArr = data.split(mark);
            TreeNode[] nodeArr = new TreeNode[valArr.length];
            for (int i = 0; i < valArr.length; i++) {
                String val = valArr[i];

                // 处理null
                if (!val.equals("null")) {
                    TreeNode node = new TreeNode(Integer.valueOf(val));
                    nodeArr[i] = node;

                    // 根节点无父节点
                    if (i > 0) {
                        // 关联父节点
                        TreeNode parent = nodeArr[(i-1)/2];
                        if (i % 2 == 1) {
                            parent.left = node;
                        } else {
                            parent.right = node;
                        }
                    }
                }
            }

            return nodeArr[0];
        }
    }
}
