package me.luliru.parctice.algorithm.string;

import java.util.Objects;

/**
 * 比较含退格的字符串
 * Created by luliru on 2020/10/19.
 */
public class 比较含退格的字符串 {

    public static void main(String[] args) {
        new 比较含退格的字符串().backspaceCompare("ab##","c#d#");
    }

    public boolean backspaceCompare(String S, String T) {
        int sIndex = S.length()-1;
        int tIndex = T.length()-1;
        int sPount = 0;
        int tPount = 0;
        // 一次往前遍历
        while (sIndex >= 0 || tIndex >= 0) {
            Character sChar = null;
            if(sIndex >=0) {
                sChar = S.charAt(sIndex);
                if(sChar == '#'){
                    sPount++;
                    sIndex--;
                    continue;
                }
                if(sPount > 0) {
                    sPount--;
                    sIndex--;
                    continue;
                }
            }
            Character tChar = null;
            if(tIndex >=0){
                tChar = T.charAt(tIndex);
                if((tChar == '#')) {
                    tPount++;
                    tIndex--;
                    continue;
                }
                if(tPount > 0) {
                    tPount--;
                    tIndex--;
                    continue;
                }
            }
            if(!Objects.equals(sChar,tChar)){
                return false;
            }
            sIndex--;
            tIndex--;
        }
        // 有字符串没遍历完
        if(sIndex >= 0 || tIndex >= 0) {
            return false;
        }
        return true;
    }
}
