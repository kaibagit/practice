package me.luliru.parctice.algorithm.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 三数之和 {

    public List<List<Integer>> threeSum(int[] nums) {
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
