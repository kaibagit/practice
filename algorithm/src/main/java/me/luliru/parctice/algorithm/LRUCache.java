package me.luliru.parctice.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * LRUCache
 ["LRUCache","put","put","get","put","get","put","get","get","get"]
 [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]

 ["LRUCache","put","get","put","get","get"]
 [[1],[2,1],[2],[3,2],[2],[3]]

 * Created by luliru on 2020/10/6.
 */
public class LRUCache {

    public static void main(String[] args) {
//        LRUCache cache = new LRUCache(2);
//        cache.put(1,1);
//        cache.put(2,2);
//        System.out.println(cache.get(1));
//        cache.put(3,3);
//        System.out.println(cache.get(2));
//        cache.put(4,4);
//        System.out.println(cache.get(1));
//        System.out.println(cache.get(3));
//        System.out.println(cache.get(4));

        LRUCache cache = new LRUCache(1);
        cache.put(2,1);
        System.out.println(cache.get(2));
        cache.put(3,2);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
    }

    private int capacity;

    private int size;

    private Node head;

    private Node tail;

    private Map<Integer,Node> map;

    public LRUCache(int capacity) {
        map = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = map.get(key);
        if(node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if(node == null) {
            node = new Node(key,value);
            add(node);
            map.put(key,node);
        }else {
            node.value = value;
            moveToHead(node);
        }
    }

    private void add(Node node) {
        if(head == null) {
            head = node;
            tail = node;
        } else {
            if(capacity == size) {
                map.remove(tail.key);
                tail = tail.pre;
                if(tail != null) {
                    tail.next = null;
                }
                size--;
            }
            head.pre = node;
            node.next = head;
            head = node;
        }
        size++;
    }

    private void moveToHead(Node node) {
        Node preNode = node.pre;
        Node nextNode = node.next;
        if(preNode != null) {
            preNode.next = nextNode;
        }
        if(nextNode != null) {
            nextNode.pre = preNode;
        }
        // Node正好在队尾，且不止node一个节点
        if(tail == node && preNode != null) {
            tail = preNode;
        }
        head.pre = node;
        node.next = head;
        node.pre = null;
        head = node;
    }

    static class Node{
        private int key;

        private int value;

        private Node next;

        private Node pre;

        private Node(int key,int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
