package me.luliru.parctice.algorithm.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 无重复字符的最长子串
 * Created by luliru on 2020/10/15.
 */
public class 无重复字符的最长子串 {

    /**
     * 滑动串口：如果没重复，则窗口right指针移动；如果重复，则窗口left指针移动到上次重复位置后面
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int start = -1;     //滑动窗口的开始位置
        Map<Character,Integer/*最后一次出现的索引值*/> lastIndexMap = new HashMap<>();
        for(int i=0;i<s.length();i++) {
            Character c = s.charAt(i);
            Integer lastIndex = lastIndexMap.get(c);
            // 从未出现过，或者前一个索引值已失效，则累计长度
            if(lastIndex == null || start > lastIndex) {
                int length = i - start;
                maxLength = Math.max(maxLength,length);
            }else {     // 出现过，则长度为当前这2个索引位置间距
                start = lastIndex;
            }
            lastIndexMap.put(c,i);
        }

        return maxLength;
    }

    /**
     * 自己实现的滑动串口
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring_v2(String s) {
        Map<Character,Integer> positions = new HashMap<>();
        int left = 0;
        int max = 0;
        for(int right =0;right < s.length();right++) {
            Character c = s.charAt(right);
            Integer position = positions.get(c);
            if(position == null || position < left) {
            } else {
                int distance = right - left;
                left = position + 1;
                max = Math.max(max,distance);
            }
            positions.put(c,right);
        }
        // 处理最后一个元素
        int distance = s.length() - left;
        max = Math.max(max,distance);
        return max;
    }

    /**
     * 暴力求解+hashset
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring_v1(String s) {
        int max = 0;
        int count = 0;
        Set<Character> set = new HashSet<>();
        for(int i=0;i<s.length();i++) {
            // 依次往后遍历，查找最长不重复的长度
            for(int j=i;j<s.length();j++) {
                Character c = s.charAt(j);
                if(set.add(c)) {
                    count ++;
                } else {    // 发现重复，则中断，并计算长度
                    max = max<count?count:max;
                    count = 0;
                    break;
                }
            }
            // 处理遍历到最后一个元素的长度计算
            max = max<count?count:max;

            // 重置，开始下一次迭代
            count = 0;
            set.clear();
        }
        return max;
    }
}
