package me.luliru.parctice.algorithm.tree;

/**
 * 剑指Offer36二叉搜索树与双向链表
 * Created by luliru on 2020/10/28.
 */
public class 剑指Offer36二叉搜索树与双向链表 {

    private Node last = null;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return root;
        }

        Node dummy = new Node();
        dummy.right = root;
        last = dummy;

        // 形成双向链表
        dfs(root);
        // 将最后节点指向头部
        last.right = dummy.right;
        dummy.right.left = last;

        return dummy.right;
    }

    private void dfs(Node node) {
//        if (node == null) {
//            return;
//        }

        Node left = node.left;
        Node right = node.right;

        if (left != null) {
            dfs(left);
        }

        last.right = node;
        node.left = last;
        last = node;

        if (right != null) {
            dfs(right);
        }
    }
}
