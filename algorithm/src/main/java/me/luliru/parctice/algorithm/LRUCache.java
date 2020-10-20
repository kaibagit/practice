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

//        LRUCache cache = new LRUCache(1);
//        cache.put(2,1);
//        System.out.println(cache.get(2));
//        cache.put(3,2);
//        System.out.println(cache.get(2));
//        System.out.println(cache.get(3));

        LRUCache cache = new LRUCache(3);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.put(4,4);
        cache.get(4);
        cache.get(3);
        cache.get(2);
        cache.get(1);
        cache.put(5,5);
        cache.get(1);
        cache.get(2);
        cache.get(3);
        cache.get(4);
        cache.get(5);
    }

    private int capacity;

    private int size;

    private Node head;

    private Node tail;

    private Map<Integer,Node> map;

    public LRUCache(int capacity) {
        map = new HashMap<>(capacity);
        this.capacity = capacity;
        head = new Node(0,0);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node existedNode = map.get(key);
        if(existedNode == null) {
            return -1;
        }
        removeFromLink(existedNode);
        addToHead(existedNode);
        return existedNode.value;
    }

    public void put(int key, int value) {
        Node existedNode = map.get(key);
        if(existedNode == null) {
            Node node = new Node(key,value);
            map.put(key,node);
            addToHead(node);
            size++;
        } else {
            removeFromLink(existedNode);
            existedNode.value = value;
            addToHead(existedNode);
        }
        if(size > capacity) {
            map.remove(tail.pre.key);
            removeFromLink(tail.pre);
            size--;
        }
    }

    private void addToHead(Node node) {
        node.pre = head;
        node.next = head.next;

        head.next.pre = node;
        head.next = node;
    }

    private void removeFromLink(Node node) {
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        next.pre = pre;
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
