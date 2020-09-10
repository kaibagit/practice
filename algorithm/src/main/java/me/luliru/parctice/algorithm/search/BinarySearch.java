package me.luliru.parctice.algorithm.search;

/**
 * BinarySearch
 * Created by luliru on 2020/8/16.
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] a = {0,10,20,30,40,50,60,70,80,90};
        System.out.println(myBinarySearch(a,43));
        System.out.println(myBinarySearch(a,70));
        int[] b = {0,10,20,30,40,50,60,70,80,90,100};
        System.out.println(myBinarySearch(b,43));
        System.out.println(myBinarySearch(b,70));
        System.out.println(myBinarySearch(b,0));
    }

    public static int myBinarySearchWithRecursion(int [] a, int x) {
        return _myBinarySearchWithRecursion(a,x,0,a.length-1);
    }

    public static int _myBinarySearchWithRecursion(int [] a, int x, int left, int right) {
        if(left >= right) {
            return x == a[left]?left:-1;
        }
        int middle = (left + right) / 2;
        int middleValue = a[middle];
        if(x == middleValue) {
            return middle;
        } else if(x < middleValue){
            return _myBinarySearchWithRecursion(a,x,left,middle-1);
        } else {
            return _myBinarySearchWithRecursion(a,x,middle+1,right);
        }
    }

    public static int myBinarySearch(int[] a,int x) {
        int left = 0;
        int right = a.length - 1;
        while ((left <= right)) {
            int middle = (left + right) / 2;
            int middleValue = a[middle];
            if(x == middleValue) {
                return middle;
            }else if(x < middleValue) {
                right = middle -1;
            }else{
                left = middle + 1;
            }
        }
        return -1;
    }
}
