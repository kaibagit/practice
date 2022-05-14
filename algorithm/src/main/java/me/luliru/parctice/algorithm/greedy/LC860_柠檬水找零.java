package me.luliru.parctice.algorithm.greedy;

/**
 * LC860_柠檬水找零
 * Created by luliru on 2022/5/9.
 */
public class LC860_柠檬水找零 {

    public static void main(String[] args) {
        int[] bills = new int[]{5,5,5,10,20};
        System.out.println(new LC860_柠檬水找零().lemonadeChange(bills));
    }

    public boolean lemonadeChange(int[] bills) {
        int count5 = 0;
        int count10 = 0;
        for (int i = 0; i < bills.length; ++i) {
            int bill = bills[i];
            if (bill == 5) {
                count5++;
            }

            if (bill == 10) {
                if (count5 > 0) {
                    count5--;
                    count10++;
                } else {
                    return false;
                }
            }

            if (bill == 20) {
                if (count10 > 0) {
                    // 1张10元，1张5元
                    if (count5 > 0) {
                        count5--;
                        count10--;
                    } else {
                        return false;
                    }
                } else {
                    // 3张5元
                    if (count5 > 2) {
                        count5 = count5 - 3;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
