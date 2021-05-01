package me.luliru.parctice.algorithm.tree;

/**
 * 剑指Offer36二叉搜索树与双向链表
 * Created by luliru on 2020/10/28.
 */
public class 剑指Offer36_二叉搜索树与双向链表 {

    private Node linkLastNode;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Node dummy = new Node();
        linkLastNode = dummy;

        dfs(root);

        // 链表头尾互相指向
        dummy.right.left = linkLastNode;
        linkLastNode.right = dummy.right;

        return dummy.right;
    }

    private void dfs(Node node) {
        // 处理左节点
        if (node.left != null) {
            dfs(node.left);
        }

        // 处理根节点
        linkLastNode.right = node;
        node.left = linkLastNode;
        linkLastNode = node;

        // 处理右节点
        if (node.right != null) {
            dfs(node.right);
        }
    }













































    private Node last = null;

    public Node treeToDoublyList_old(Node root) {
        if (root == null) {
            return root;
        }

        Node dummy = new Node();
        dummy.right = root;
        last = dummy;

        // 形成双向链表
        dfs_old(root);
        // 将最后节点指向头部
        last.right = dummy.right;
        dummy.right.left = last;

        return dummy.right;
    }

    private void dfs_old(Node node) {
//        if (node == null) {
//            return;
//        }

        Node left = node.left;
        Node right = node.right;

        if (left != null) {
            dfs_old(left);
        }

        last.right = node;
        node.left = last;
        last = node;

        if (right != null) {
            dfs_old(right);
        }
    }
}
