package me.luliru.parctice.algorithm;

/**
 * LC1603_设计停车系统
 * Created by luliru on 2021/3/19.
 */
public class LC1603_设计停车系统 {
}

class ParkingSystem {

    private int big;

    private int medium;

    private int small;

    public ParkingSystem(int big, int medium, int small) {
        this.big = big;
        this.medium = medium;
        this.small = small;
    }

    public boolean addCar(int carType) {
        if (carType == 1) {
            return big-- > 0;
        } else if (carType == 2) {
            return medium-- > 0;
        } else {
            return small-- > 0;
        }
    }
}