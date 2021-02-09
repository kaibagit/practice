package me.luliru.parctice.algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 划分字母区间
 * Created by luliru on 2020/10/22.
 */
public class 划分字母区间 {

    public List<Integer> partitionLabels(String S) {
        List<Integer> ans = new ArrayList<>();

        if(S == null || S.length() == 0) {
            return ans;
        }

        // 遍历sting，获取每个字符最后出现的位置
        Map<Character,Integer/* 最后出现的位置 */> lastIndexMap = new HashMap<>();
        for(int i=0;i<S.length();i++) {
            lastIndexMap.put(S.charAt(i),i);
        }

        int start = 0;
        int point = start;
        int end = start;
        while (start < S.length()) {
            while (point <= end) {
                char c = S.charAt(point);
                int lastIndex = lastIndexMap.get(c);
                end = Math.max(end,lastIndex);
                point++;
            }
            int length = end - start + 1;
            ans.add(length);
            start = end + 1;
            point = start;
            end = start;
        }

        return ans;
    }


}
