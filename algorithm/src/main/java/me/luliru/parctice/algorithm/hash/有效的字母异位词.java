package me.luliru.parctice.algorithm.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 有效的字母异位词
 * Created by luliru on 2021/2/11.
 */
public class 有效的字母异位词 {

    public static void main(String[] args) {
        new 有效的字母异位词().isAnagram_v2("aacc","ccac");
    }

    /**
     * Hash方法二，及时中断的优化版本
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram_v2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            Integer count = countMap.getOrDefault(c, 0);
            count++;
            countMap.put(c, count);
        }
        for (int i = 0; i < t.length(); i++) {
            Character c = t.charAt(i);
            Integer count = countMap.getOrDefault(c, 0);
            if (count == 0) {
                return false;
            }
            count--;
            countMap.put(c, count);
        }
        return true;
    }

    /**
     * Hash方法一
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram_v1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            Integer count = sMap.getOrDefault(c, 0);
            count++;
            sMap.put(c, count);
        }
        for (int i = 0; i < t.length(); i++) {
            Character c = t.charAt(i);
            Integer count = tMap.getOrDefault(c, 0);
            count++;
            tMap.put(c, count);
        }
        return sMap.equals(tMap);
    }
}
