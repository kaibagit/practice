package me.luliru.parctice.algorithm.tree;

import java.util.LinkedList;

/**
 * 填充每个节点的下一个右侧节点指针
 * Created by luliru on 2020/10/15.
 */
public class 填充每个节点的下一个右侧节点指针 {

    public Node connect(Node root) {
        if(root == null) return null;
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);

        int levelCount = 1;    // 每一层的node个数
        Node lastNode = null;   //该层上一个节点
        while (!queue.isEmpty()) {
            // 循环该层元素
            for(int i=0;i<levelCount;i++) {
                Node node = queue.pollFirst();
                // 如果存在下一次node，则将其插入队列
                if(node.left != null) {
                    queue.addLast(node.left);
                    queue.addLast(node.right);
                }
                // 将前一个node指向当前Node
                if(lastNode != null) {
                    lastNode.next = node;
                }
                lastNode = node;
            }

            // 进入下一层循环
            levelCount = levelCount * 2;
            lastNode = null;
        }
        return root;
    }
}
