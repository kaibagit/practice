package me.luliru.parctice.algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC90_子集II
 * Created by luliru on 2021/4/1.
 */
public class LC90_子集II {

    /**
     * 排序 + 回溯
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> choosed = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, ans, choosed, -1, used);
        return ans;
    }

    private void backtrack(int[] nums, List<List<Integer>> ans, List<Integer> choosed, int lastIndex, boolean[] used) {
        ans.add(new ArrayList<>(choosed));
        // 终止条件：choosed长度<=nums.length
        if (choosed.size() == nums.length) {
            return;
        }

        for (int i = lastIndex + 1; i < nums.length; i++) {
            // 剪枝
            if (used[i]) {      // 排除重复选择同一元素
                continue;
            }
            if (i > 0 && nums[i - 1] == nums[i] && used[i - 1] == false) {  // 上次撤销的元素，跟这次选择的元素是同一个，去重
                continue;
            }

            // 做选择
            choosed.add(nums[i]);
            used[i] = true;
            backtrack(nums, ans, choosed, i, used);

            // 撤销选择
            choosed.remove(choosed.size() - 1);
            used[i] = false;
        }
    }
}
