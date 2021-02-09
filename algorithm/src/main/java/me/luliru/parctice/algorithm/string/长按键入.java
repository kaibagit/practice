package me.luliru.parctice.algorithm.string;

/**
 * 长按键入
 * Created by luliru on 2020/10/21.
 */
public class 长按键入 {

    public boolean isLongPressedName(String name, String typed) {
        if(name.length() == 0 && typed.length() == 0) {
            return true;
        }

        int i = 0;
        int j = 0;
        while (j < typed.length()) {
            if(i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            }else if(i> 0 && typed.charAt(j) == name.charAt(i-1)) {
                j++;
            }else {
                return false;
            }
        }

        if(i == name.length()) {
            return true;
        }

        return false;
    }


}
