package me.luliru.parctice.algorithm.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串中的第一个唯一字符
 * Created by luliru on 2020/12/23.
 */
public class 字符串中的第一个唯一字符 {

    public int firstUniqChar(String s) {
        Map<Character, Integer> indexMap = new HashMap<>();
        for (int i = 0; i< s.length(); i++) {
            Character ch = s.charAt(i);
            Integer existedIndex = indexMap.get(ch);
            if (existedIndex == null) {
                indexMap.put(ch, i);
            } else {
                indexMap.put(ch, -1);
            }
        }
        int res = s.length();
        for (Map.Entry<Character, Integer> entry : indexMap.entrySet()) {
            if (entry.getValue() != -1 && entry.getValue() < res) {
                res = entry.getValue();
            }
        }

        if (res == s.length()) {
            return -1;
        }
        return res;
    }
}
