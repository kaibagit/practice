package com.kaiba.demo.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        int[] arr = {2,4,1};
        System.out.println(maxProfit(arr));
    }

    public static int maxProfit(int[] prices) {
        if(prices == null || prices.length < 1) {
            return 0;
        }
        int buying = prices[0];
        int profit = 0;
        for(int i=1;i<prices.length;i++) {
            int price = prices[i];
            if(price - buying > profit) {
                profit = price - buying;
            }
            if(price < buying) {
                buying = price;
            }
        }
        return profit;
    }

    public static int lengthOfLongestSubstring(String s) {
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
                max = max > distance? max:distance;
            }
            positions.put(c,right);
        }
        int distance = s.length() - left;
        max = max > distance? max:distance;
        return max;
    }
}
