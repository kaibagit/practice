package me.luliru.parctice.algorithm.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 找到所有数组中消失的数字

 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。

 找到所有在 [1, n] 范围之间没有出现在数组中的数字。

 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。

 示例:

 输入:
 [4,3,2,7,8,2,3,1]

 输出:
 [5,6]


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by luliru on 2020/8/30.
 */
public class 找到所有数组中消失的数字 {

    public static void main(String[] args) {
//        int[] arr = {4,3,2,7,8,2,3,1};
        int[] arr = {2,1};
        List<Integer> result = mine_findDisappearedNumbers(arr);
        for(Integer i : result) {
            System.out.print(i+",");
        }
    }

    public static List<Integer> mine_findDisappearedNumbers(int[] nums) {
        for(int i=0;i<nums.length;i++) {
            int x = nums[i];
            if(x != i+1) {
                swap(nums,x);
                nums[i] = 0;
            }
        }
        List<Integer> result = new LinkedList<>();
        for(int i=0;i<nums.length;i++) {
            if(nums[i] == 0) {
                result.add(i+1);
            }
        }
        return result;
    }

    private static void swap(int[] nums,int x) {
        if(x ==0) {
            return;
        }
        int v = nums[x-1];
        if(v != x) {
            nums[x-1] = x;
            swap(nums,v);
        }
    }
}
