package com.kaiba.demo.algorithm.search;

import java.util.Arrays;

/**
 * Created by luliru on 2017/3/8.
 */
public class BinarySearch {

    public static void main(String[] args){
        int[] arr = {10,20,30,50,60,77,89,90,102,110,120,134};
        System.out.println(binarySearch(arr,89));
    }

    private static int binarySearch(int[] arr, int target){
        int middle = arr[arr.length/2];
        if(target == middle){
            return middle;
        }
        if(arr.length == 1){
            return -1;
        }
        int[] subArr = null;
        if(target > middle){
            subArr = Arrays.copyOfRange(arr,arr.length/2,arr.length);
        }else{
            subArr = Arrays.copyOfRange(arr,0,arr.length/2);
        }
        return binarySearch(subArr,target);
    }

}
