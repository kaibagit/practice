package me.luliru.parctice.algorithm.array;

import java.util.*;

public class 三数之和 {

    /**
     * 排序+双指针
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums.length < 3) {
            return res;
        }

        Arrays.sort(nums);

        for(int i=0;i<nums.length;i++) {
            if(i>0 && nums[i] == nums[i-1]) {   // 与上次迭代相比，跳过重复的
                continue;
            }
            int right = nums.length-1;
            int twoSum = 0-nums[i];     // 剩余2数之和

            // 开始对第二个数进行遍历
            for(int j=i+1;j<nums.length;j++) {
                if(j>i+1 && nums[j] == nums[j-1]) { // 与上次迭代相比，跳过重复的
                    continue;
                }
                // 第三个数从尾部开始回溯
                while (j < right) {
                    int currSum = nums[j] + nums[right];
                    if(currSum == twoSum) {
                        List<Integer> ans = new ArrayList<>();
                        ans.add(nums[i]);
                        ans.add(nums[j]);
                        ans.add(nums[right]);
                        res.add(ans);
                        break;
                    }else if(currSum > twoSum) {
                        right --;
                    }else{
                        break;
                    }
                }
            }
        }

        return res;
    }

    /**
     * 双重循环+hash
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum_1(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();

        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            int val = nums[i];
            List<Integer> coll = map.get(val);
            if(coll == null) {
                coll = new ArrayList<>();
                map.put(val,coll);
            }
            coll.add(i);
        }

        for(int i=0;i<nums.length;i++) {
            int iVal = nums[i];
            for(int j=i+1;j<nums.length;j++) {
                int jVal = nums[j];
                int target = -(iVal+jVal);
                List<Integer> coll = map.get(target);
                if(coll == null) continue;
                for(int k=0;k<coll.size();k++) {
                    int thirdIndex = coll.get(k);
                    if(thirdIndex != i && thirdIndex != j) {
                        List<Integer> solution = new ArrayList<>();
                        solution.add(iVal);
                        solution.add(jVal);
                        solution.add(target);
                        output.add(solution);
                    }
                }
            }
        }

        return output;
    }
}
