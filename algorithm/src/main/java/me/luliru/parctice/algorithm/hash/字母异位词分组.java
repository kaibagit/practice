package me.luliru.parctice.algorithm.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字母异位词分组
 * Created by luliru on 2020/12/14.
 */
public class 字母异位词分组 {

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();

        Map<String/* 模式 */, List<String> /* 匹配的字符串 */> groupedStrings = new HashMap<>();
        for (String str : strs) {
            // 1、将字符串转化为26个字母的计数表示方式，如：1#2#3
            int[] counts = new int[26];
            for (char c : str.toCharArray()) {
                counts[c - 'a']++;
            }
            StringBuilder patternBuilder = new StringBuilder();
            for (int count : counts) {
                patternBuilder.append(count).append('#');
            }
            String pattern = patternBuilder.toString();
            // 2、将该字符串加入到对应分组里
            List<String> group = groupedStrings.get(pattern);
            if (group == null) {
                group = new ArrayList<>();
                groupedStrings.put(pattern, group);
            }
            group.add(str);
        }

        for (Map.Entry<String, List<String>> entry : groupedStrings.entrySet()) {
            ans.add(entry.getValue());
        }
        return ans;
    }
}
