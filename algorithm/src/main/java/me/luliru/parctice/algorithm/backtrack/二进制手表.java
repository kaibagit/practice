package me.luliru.parctice.algorithm.backtrack;

import java.util.*;

/**
 * 二进制手表
 * Created by luliru on 2020/10/6.
 */
public class 二进制手表 {

    public static void main(String[] args) {
        List<String> res = new 二进制手表().readBinaryWatch(2);
        System.out.println(res);
    }

    private Set<Integer> selected = new HashSet<>();

    private List<String> res = new ArrayList<>();

    private int[] hourVal = new int[]{0,1,2,4,8};
    private int[] secondVal = new int[]{0,0,0,0,0,1,2,4,8,16,32};

    public List<String> readBinaryWatch(int num) {
        dfs(num,0,0,0);
        return res;
    }

    public void dfs(int n,int selectedCount,int hours,int seconds) {
        if(n == selectedCount) {
            String str = convertToString(selected);
            res.add(str);
            return;
        }

        for(int i=1;i<=10;i++) {
            // 剪纸
            if(selected.contains(i)) {
                return;
            }
            int hoursAfterChoose = hours;
            int secondsAfterChoose = seconds;
            if(isHour(i)){
                hoursAfterChoose = hoursAfterChoose + getHourVal(i);
                if(hoursAfterChoose > 11) {
                    return;
                }
            } else {
                secondsAfterChoose = secondsAfterChoose + getSecondVal(i);
                if(secondsAfterChoose > 59) {
                    return;
                }
            }

            // 递归
            selected.add(i);
            dfs(n,selectedCount+1,hoursAfterChoose,secondsAfterChoose);

            // 回退
            selected.remove(i);
        }
    }

    private boolean isHour(int n) {
        if(n <= 4) {
            return true;
        }
        return false;
    }

    private int getHourVal(int n) {
        return hourVal[n];
    }

    private int getSecondVal(int n) {
        return secondVal[n];
    }

    private String convertToString(Set<Integer> set) {
        int hours = 0;
        int seconds = 0;
        Iterator<Integer> interator = set.iterator();
        while (interator.hasNext()) {
            int i = interator.next();
            if(isHour(i)){
                hours += getHourVal(i);
            }else{
                seconds += getSecondVal(i);
            }
        }

        return String.format("%d:%02d",hours,seconds);
    }
}
