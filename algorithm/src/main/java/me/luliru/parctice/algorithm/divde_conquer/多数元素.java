package me.luliru.parctice.algorithm.divde_conquer;

import java.util.HashMap;
import java.util.Map;

/**
 * 多数元素
 * Created by luliru on 2021/2/13.
 */
public class 多数元素 {

    /**
     * 哈希
     * @param nums
     * @return
     */
    public int majorityElement_hash(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int count = countMap.getOrDefault(num, 0);
            countMap.put(num, count + 1);
        }
        int majority = 0;
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                majority = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return majority;
    }

    /**
     * 投票算法
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (count == 0) {
                candidate = num;
                count++;
            } else if (candidate == num) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }
}
