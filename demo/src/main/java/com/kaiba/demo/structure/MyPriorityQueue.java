package com.kaiba.demo.structure;

import java.util.*;

/**
 * Created by luliru on 2017/3/14.
 */
public class MyPriorityQueue<T> implements Queue<T> {

    private Comparator<T> comparator;

    private Object[] queue;

    private int size;

    public MyPriorityQueue(Comparator<T> comparator){
        this.comparator = comparator;
        this.queue = new Object[16];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOfRange(queue,0,size);
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public boolean add(T o) {
        return offer(o);
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public boolean offer(T o) {
        int sizeBefore = size++;
        if(size > queue.length){
            grow();
        }
        if(sizeBefore == 0){
            queue[0] = o;
        }else{
            siftUp(sizeBefore,o);
        }
        return true;
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public T poll() {
        if(size == 0){
            return null;
        }
        T result = (T) queue[0];
        T t = (T) queue[--size];
        queue[size] = null;
        siftDown(0,t);
        return result;
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    private void siftUp(int index,T element){
        while(index > 0){
            int parent = index/2;
            T parentElement = (T)queue[parent];
            if(comparator.compare(parentElement,element) > 0){
                queue[index] = element;
                break;
            }else{
                queue[index] = parentElement;
                index = parent;
            }
        }
        if(index == 0){
            queue[index] = element;
        }
    }

    private void siftDown(int index,T element){
        int haveChildrenMaxIndex = size/2 - 1;  //由2i+2<=size-1导出
        while(index <= haveChildrenMaxIndex){
            int leftChildIndex = index * 2 + 1;
            int rightChildIndex = leftChildIndex + 1;
            int compareTargetIndex = leftChildIndex;
            T compareTarget = (T) queue[leftChildIndex];
            if(rightChildIndex <= size-1){
                T rightChild = (T) queue[rightChildIndex];
                if(comparator.compare(rightChild,compareTarget) > 0){
                    compareTargetIndex = rightChildIndex;
                    compareTarget = rightChild;
                }
            }
            if(comparator.compare(element,compareTarget) > 0){
                break;
            }else{
                queue[index] = compareTarget;
                index = compareTargetIndex;
            }
        }
        queue[index] = element;
    }

    private void siftDown0(int index,T element){
        int maxIndex = size - 1;
        int leftChild = index * 2 + 1;
        int rightChild = index * 2 + 2;
        boolean succ = false;
        while(!succ &&leftChild <= maxIndex){
            int compareIndex = leftChild;
            T compareTarget = (T) queue[leftChild];
            if(rightChild <= maxIndex){
                T rc = (T) queue[rightChild];
                if(comparator.compare(rc,compareTarget) > 0){
                    compareTarget = rc;
                    compareIndex = rightChild;
                }
            }
            if(comparator.compare(element,compareTarget) > 0){
                queue[index] = element;
                succ= true;
            }else{
                queue[index] = compareTarget;
                index = compareIndex;
                leftChild = index * 2 + 1;
                rightChild = index * 2 + 2;
            }
        }
        if(!succ){
            queue[index] = element;
        }

    }

    private void grow(){
        int newCapacity = queue.length + queue.length/2;
        queue = Arrays.copyOf(queue,newCapacity);
    }

    public static void main(String[] args){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        };

        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(comparator);
        for(int i=0;i<20;i++){
            queue.add(i);
        }
        for(Object i : queue.toArray()){
            System.out.println(i);
        }
        System.out.println("==========================");
        Object t = queue.poll();
        while(t != null){
            System.out.println(t);
            t = queue.poll();
        }
    }
}
