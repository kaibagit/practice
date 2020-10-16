package me.luliru.parctice.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指Offer50第一个只出现一次的字符
 * Created by luliru on 2020/10/13.
 */
public class 剑指Offer50第一个只出现一次的字符 {

    public char firstUniqChar(String s) {
        Map<Character,Integer/*字符出现的索引位置，负数表示重复出现*/> map = new HashMap<>();
        for(int i=1;i<= s.length();i++) {
            Character c = s.charAt(i-1);
            Integer existedIndex = map.get(c);
            if(existedIndex == null) {
                map.put(c,i);
            } else if(existedIndex > 0) {
                map.put(c,i * -1);
            }
        }
        Integer appearIndex = Integer.MAX_VALUE;
        char result = ' ';
        for(Map.Entry<Character,Integer> entry : map.entrySet()) {
            Integer index = entry.getValue();
            if(index > 0 && index < appearIndex) {
                appearIndex = index;
                result = entry.getKey();
            }
        }
        return result;
    }
}
