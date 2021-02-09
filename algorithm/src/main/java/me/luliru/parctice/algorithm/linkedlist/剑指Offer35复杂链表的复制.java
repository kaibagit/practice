package me.luliru.parctice.algorithm.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指Offer35复杂链表的复制
 * Created by luliru on 2020/10/27.
 */
public class 剑指Offer35复杂链表的复制 {

    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }

        Map<Node/* ori node */,Node/* copy */> map = new HashMap<>();
        Node node = head;
        while (node != null) {
            Node newNode = new Node(node.val);
            map.put(node,newNode);
            node = node.next;
        }

        node = head;
        while (node != null) {
            Node newNode = map.get(node);
            newNode.next = map.get(node.next);
            newNode.random = map.get(node.random);
            node = node.next;
        }

        return map.get(head);
    }
}
