package me.luliru.parctice.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * LC146_LRU缓存机制
 * Created by luliru on 2021/3/5.
 */
public class LC146_LRU缓存机制 {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        System.out.println("null");
        cache.put(1,1);
        System.out.println("null");
        cache.put(2,2);
        System.out.println("null");
        System.out.println(cache.get(1));
        cache.put(3,3);
        System.out.println("null");
        System.out.println(cache.get(2));
        cache.put(4,4);
        System.out.println("null");
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));

//        LRUCache cache = new LRUCache(2);
//        System.out.println("null");
//        cache.put(2, 1);
//        System.out.println("null");
//        cache.put(3, 2);
//        System.out.println("null");
//        System.out.println(cache.get(3));
//        System.out.println(cache.get(2));
//        cache.put(4,2);
//        System.out.println("null");
//        System.out.println(cache.get(2));
//        System.out.println(cache.get(3));
//        System.out.println(cache.get(4));

//        LRUCache cache = new LRUCache(1);
//        cache.put(2,1);
//        System.out.println(cache.get(2));
//        cache.put(3,2);
//        System.out.println(cache.get(2));
//        System.out.println(cache.get(3));

//        LRUCache cache = new LRUCache(3);
//        cache.put(1,1);
//        cache.put(2,2);
//        cache.put(3,3);
//        cache.put(4,4);
//        cache.get(4);
//        cache.get(3);
//        cache.get(2);
//        cache.get(1);
//        cache.put(5,5);
//        cache.get(1);
//        cache.get(2);
//        cache.get(3);
//        cache.get(4);
//        cache.get(5);
    }

    static class LRUCache {

        private Node head;
        private Node tail;
        private Map<Integer, Node> map;
        private int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap(capacity);
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            Node node = map.get(key);
            if (node != null) {
                removeFromList(node);
                addToHead(node);
                return node.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            Node node = map.get(key);
            if (node == null) {
                node = new Node(key,value);
                map.put(key, node);
                addToHead(node);
            } else {
                removeFromList(node);
                node.value = value;
                addToHead(node);
            }
            if (map.size() > capacity) {
                map.remove(tail.pre.key);
                removeFromList(tail.pre);
            }
        }

        private void removeFromList(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        private void addToHead(Node node) {
            node.next = head.next;
            node.pre = head;
            head.next = node;
            node.next.pre = node;
        }
    }

    static class Node {
        int key;
        int value;
        Node pre;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
