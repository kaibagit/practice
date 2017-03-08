package com.kaiba.demo.algorithm.search;

import java.util.Arrays;

/**
 * Created by luliru on 2017/3/8.
 */
public class BinarySearch {

    public static void main(String[] args){
        int[] arr = {10,20,30,50,60,77,89,90,102,110,120,134};
        System.out.println(binarySearchByRecursion(arr,10));
        System.out.println(binarySearchByRecursion(arr,66));
        System.out.println(binarySearchByRecursion(arr,77));
        System.out.println(binarySearchByRecursion(arr,89));
        System.out.println(binarySearchByRecursion(arr,121));
        System.out.println(binarySearchByRecursion(arr,134));
    }

    private static int binarySearchByRecursion(int[] arr, int target){
        if(arr.length == 0){
            return -1;
        }
        int middleIndex = arr.length/2;
        int middle = arr[middleIndex];
        if(target == middle){
            return middle;
        }
        int[] subArr = null;
        if(target > middle){
            subArr = Arrays.copyOfRange(arr,middleIndex+1,arr.length);
        }else{
            subArr = Arrays.copyOfRange(arr,0,middleIndex);
        }
        return binarySearchByRecursion(subArr,target);
    }

    private static int binarySearch(int[] arr, int target){
        int minIndex = 0;
        int maxIndex = arr.length - 1;
        while(true){
            int middleIndex = (minIndex+maxIndex)/2;
            int middle = arr[middleIndex];
            if(target == middle){
                return middle;
            }
            if(minIndex == maxIndex){
                return -1;
            }
            if(target > middle){
                minIndex = middleIndex+1;
            }else{
                maxIndex = middleIndex-1;
            }
        }
    }
}
