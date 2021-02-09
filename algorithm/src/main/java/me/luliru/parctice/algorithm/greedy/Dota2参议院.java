package me.luliru.parctice.algorithm.greedy;

import java.util.LinkedList;

/**
 * Dota2参议院
 * Created by luliru on 2020/12/11.
 */
public class Dota2参议院 {

    public static void main(String[] args) {

    }

    public String predictPartyVictory(String senate) {
        LinkedList<Integer> rq = new LinkedList<>();
        LinkedList<Integer> dq = new LinkedList<>();

        int n = senate.length();
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') {
                rq.addLast(i);
            } else {
                dq.addLast(i);
            }
        }

        while (!rq.isEmpty() && !dq.isEmpty()) {
            int rIndex = rq.peekFirst();
            int dIndex = dq.peekFirst();
            if (rIndex < dIndex) {
                rq.addLast(rq.pollFirst() + n);
                dq.pollFirst();
            } else {
                dq.addLast(dq.pollFirst() + n);
                rq.pollFirst();
            }
        }

        return rq.isEmpty() ? "Dire" : "Radiant";
    }
}
