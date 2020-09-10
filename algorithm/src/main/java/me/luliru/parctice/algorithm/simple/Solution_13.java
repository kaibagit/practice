package me.luliru.parctice.algorithm.simple;

/**
 * Solution_13
 * Created by luliru on 2020-04-29.
 */
public class Solution_13 {

    public static void main(String[] args) {
        String input = "MCMXCIV";
        System.out.println(solve(input));
    }

    private static int solve(String s) {
        int value = 0;
        Character impair = null;
        for(int i=0;i<s.length();i++) {
            Character c = s.charAt(i);
            if(impair != null ){
                if (char2Value(impair) < char2Value(c)) {
                    value -= char2Value(impair);
                } else {
                    value += char2Value(impair);
                }
                impair = null;
            }

            if(c == 'I' || c == 'X' || c == 'C') {
                impair = c;
            }else {
                value += char2Value(c);
            }
        }
        if(impair != null) {
            value += char2Value(impair);
        }
        return value;
    }

    private static int char2Value(Character c) {
        int v = 0;
        switch (c) {
            case 'I' : v = 1;break;
            case 'V' : v = 5;break;
            case 'X' : v=10;break;
            case 'L' : v=50;break;
            case 'C' : v=100;break;
            case 'D' : v=500;break;
            case 'M' : v=1000;break;
        }
        return v;
    }
}
