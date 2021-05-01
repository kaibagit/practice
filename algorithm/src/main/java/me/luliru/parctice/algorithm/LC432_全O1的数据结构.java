package me.luliru.parctice.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * LC432_全O1的数据结构
 * Created by luliru on 2021/3/6.
 */
public class LC432_全O1的数据结构 {

    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        allOne.inc("a");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
        allOne.inc("b");
        allOne.inc("b");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
    }
}

class AllOne {

    private Map<String, Node> dataMap;

    private MutilNode dummyHead;

    private MutilNode dummyTail;

    /** Initialize your data structure here. */
    public AllOne() {
        dataMap = new HashMap<>();
        dummyHead = new MutilNode(0);
        dummyTail = new MutilNode(-1);
        dummyHead.next = dummyTail;
        dummyTail.pre = dummyHead;
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        Node node = dataMap.get(key);
        if (node == null) {
            node = new Node(key, 1);
            addToMutilNodeTail(node);
            dataMap.put(key, node);
        } else {
            node.val = node.val + 1;
            MutilNode preMutilNode = node.parent.pre;
            removeFromMutilNode(node);
            if (preMutilNode.val != node.val) {
                createAndAddToParent(preMutilNode, node);
            } else {
                addToMutilNode(preMutilNode, node);
            }
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        Node node = dataMap.get(key);
        if (node == null) {
            return;
        }
        if (node.val == 1) {
            removeFromMutilNode(node);
            dataMap.remove(key);
        } else {
            node.val = node.val - 1;
            MutilNode nextMutilNode = node.parent.next;
            removeFromMutilNode(node);
            if (nextMutilNode.val != node.val) {
                createAndAddToParent(node, nextMutilNode);
            } else {
                addToMutilNode(nextMutilNode, node);
            }
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return dummyHead.next.dummyFirst.down.key;
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return dummyTail.pre.dummyFirst.down.key;
    }

    /**
     * 将node插入到末尾MutilNode中
     * @param node
     */
    private void addToMutilNodeTail(Node node) {
        MutilNode tail = dummyTail.pre;
        if (tail.val != 1) {   // mutilNode还未创建，创建并加入横向链表
            tail = new MutilNode(1);
            tail.pre = dummyTail.pre;
            tail.next = dummyTail;
            tail.pre.next = tail;
            tail.next.pre = tail;
        }
        // 将node加入纵向链表
        addToMutilNode(tail, node);
    }

    private void createAndAddToParent(MutilNode pre,Node node) {
        MutilNode parent = new MutilNode(node.val);
        parent.pre = pre;
        parent.next = pre.next;
        pre.next = parent;
        parent.next.pre = parent;

        addToMutilNode(parent, node);
    }

    private void createAndAddToParent(Node node, MutilNode next) {
        MutilNode parent = new MutilNode(node.val);
        parent.pre = next.pre;
        parent.next = next;
        next.pre = parent;
        parent.pre.next = parent;

        addToMutilNode(parent, node);
    }

    private void addToMutilNode(MutilNode parent, Node node) {
        node.up = parent.dummyLast.up;
        node.down = parent.dummyLast;
        parent.dummyLast.up.down = node;
        parent.dummyLast.up = node;
        node.parent = parent;
    }

    /**
     * 将node从当前MutilNode中移除
     * @param node
     */
    private void removeFromMutilNode(Node node) {
        node.up.down = node.down;
        node.down.up = node.up;
        MutilNode parent = node.parent;
        if (parent.dummyFirst.down == parent.dummyLast) {   // MutilNode已不存在Node，将其移除
            parent.pre.next = parent.next;
            parent.next.pre = parent.pre;
        }
    }
}

class Node {
    String key;
    int val;
    Node up;
    Node down;
    MutilNode parent;
    Node(String key, int val) {
        this.key = key;
        this.val = val;
    }
}

class MutilNode {
    int val;
    MutilNode pre;
    MutilNode next;
    Node dummyFirst;
    Node dummyLast;

    MutilNode(int val) {
        this.val = val;
        dummyFirst = new Node("", 0);
        dummyLast = new Node("", -1);
        dummyFirst.down = dummyLast;
        dummyLast.up = dummyFirst;
    }
}
