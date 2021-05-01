package me.luliru.parctice.algorithm.array;

import java.util.*;

public class LC15_三数之和 {

    public static void main(String[] args) {
        new LC15_三数之和().threeSum_210401_v2(new int[]{-1,0,1,2,-1,-4});
    }


    /**
     * 排序 + 哈希 + 去重
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum_210401_v1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        // 1、排序
        Arrays.sort(nums);

        // 2、构建hash
        Map<Integer, List<Integer>> valToIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> indexes = valToIndexMap.get(nums[i]);
            if (indexes == null) {
                indexes = new ArrayList<>();
                valToIndexMap.put(nums[i], indexes);
            }
            indexes.add(i);
        }

        // 3、2层循环，查找第三个值
        List<Integer> preSol = Collections.emptyList();
        for (int i = 0; i < nums.length - 1; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {      // 去重，如果当前的i与上一次i相同，计算有结果，也是重复结果，过滤掉
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {      // 去重，如果当前的j与上一次j相同，计算有结果，也是重复结果，过滤掉
                    continue;
                }

                int third = 0 - nums[i] - nums[j];
                List<Integer> indexes = valToIndexMap.get(third);
                if (indexes == null) {
                    continue;
                }
                for (int k = 0; k < indexes.size(); k++) {
                    if (j < indexes.get(k)) {       // 由于之前已经排序过了，所以只要保证第三个数的索引值在i、j后面即可不会重复选择
                        List<Integer> sol = new ArrayList<>();
                        sol.add(nums[i]);
                        sol.add(nums[j]);
                        sol.add(third);
                        ans.add(sol);
                        break;
                    }
                }
            }
        }

        return ans;
    }

    /**
     * 排序 + 双三指针 + 去重
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum_210401_v2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        // 1、排序
        Arrays.sort(nums);

        // 2、1层循环 + 双指针
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {      // 去重，如果当前的i与上一次i相同，计算有结果，也是重复结果，过滤掉
                continue;
            }
            int targetTwoSum = -nums[i];
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                if (left > i + 1 && nums[left] == nums[left - 1]) {
                    ++left;
                    continue;
                }
                int twoSum = nums[left] + nums[right];
                if (targetTwoSum < twoSum) {
                    --right;
                } else if (targetTwoSum == twoSum) {
                    List<Integer> sol = new ArrayList<>();
                    sol.add(nums[i]);
                    sol.add(nums[left]);
                    sol.add(nums[right]);
                    ans.add(sol);
                    ++left;
                    continue;
                } else if (targetTwoSum > twoSum) {
                    ++left;
                }
            }
        }

        return ans;
    }














































    /**
     * 排序+双指针
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum_old(int[] nums) {
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
    public List<List<Integer>> threeSum_old_1(int[] nums) {
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
