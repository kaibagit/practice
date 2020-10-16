package me.luliru.parctice.algorithm.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 两个数组的交集II
 * Created by luliru on 2020/9/25.
 */
public class 两个数组的交集II {

    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> ans = new ArrayList<>();
        int[] hashTarget = nums1;
        int[] loopArray = nums2;
        if(nums1.length > nums2.length) {
            hashTarget = nums2;
            loopArray = nums1;
        }
        Map<Integer,Integer> countMap = new HashMap<>(hashTarget.length);
        for(int i=0;i<hashTarget.length;i++) {
            int val = hashTarget[i];
            Integer count = countMap.get(val);
            if(count == null) {
                count = 0;
            }
            count++;
            countMap.put(val,count);
        }

        for(int i=0;i<loopArray.length;i++) {
            int val = loopArray[i];
            Integer count = countMap.get(val);
            if(count != null){
                ans.add(val);
                count--;
                if(count > 0) {
                    countMap.put(val,count);
                }else {
                    countMap.remove(val);
                }
            }
        }

        int[] result = new int[ans.size()];
        for(int i=0;i<ans.size();i++) {
            result[i] = ans.get(i);
        }
        return result;
    }
}
