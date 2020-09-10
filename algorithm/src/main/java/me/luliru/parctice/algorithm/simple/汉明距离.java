package me.luliru.parctice.algorithm.simple;

/**
 * 汉明距离
 * Created by luliru on 2020/8/27.
 */
public class 汉明距离 {

    public static void main(String[] args) {
        System.out.println(mine(1,4));
    }

    public static int mine(int x,int y) {
        int z = x ^ y;
        int count = 0;
        while(z != 0) {
            if(z % 2 == 1) {
                count = count +1;
            }
            z = z/2;
        }
        return count;
    }

    public static int mineV2(int x,int y) {
        int z = x ^ y;
        int count = 0;
        while(z != 0) {
            count = count + (z & 1);
            z = z >> 1;
        }
        return count;
    }
}
