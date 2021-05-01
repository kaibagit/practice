package me.luliru.parctice.algorithm.hash;

/**
 * LC705_设计哈希集合
 * Created by luliru on 2021/3/13.
 */
public class LC705_设计哈希集合 {


}

class MyHashSet {

    private boolean[] arr;

    /** Initialize your data structure here. */
    public MyHashSet() {
        arr = new boolean[1000000 + 1];
    }

    public void add(int key) {
        arr[key] = true;
    }

    public void remove(int key) {
        arr[key] = false;
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return arr[key] == true;
    }
}