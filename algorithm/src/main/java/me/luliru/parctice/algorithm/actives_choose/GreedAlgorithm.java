package me.luliru.parctice.algorithm.actives_choose;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 贪心算法
 * Created by luliru on 2020/8/22.
 */
public class GreedAlgorithm {

    public static void main(String[] args) {
        List<Active> activeList = new ArrayList<>();
        activeList.add(new Active(1,1,4));
        activeList.add(new Active(2,3,5));
        activeList.add(new Active(3,0,6));
        activeList.add(new Active(4,5,7));
        activeList.add(new Active(5,3,8));
        activeList.add(new Active(6,5,9));
        activeList.add(new Active(7,6,10));
        activeList.add(new Active(8,8,11));
        activeList.add(new Active(9,8,12));
        activeList.add(new Active(10,2,13));
        activeList.add(new Active(11,12,14));

        solveV2(activeList);
    }

    private static void solve(List<Active> activeList) {
        Active blankActive = new Active(0,0,24);
        List<Active> result = new ArrayList<>();
        int lastEnd = 0;
        Active choosed = blankActive;
        boolean haveMore = true;
        while(haveMore) {
            haveMore = false;
            for(int i=0;i<activeList.size();i++) {
                Active current = activeList.get(i);
                if(current.begin >= lastEnd && current.end < choosed.end) {
                    choosed = current;
                    haveMore = true;
                }
            }
            if(haveMore) {
                result.add(choosed);
                lastEnd = choosed.end;
                choosed = blankActive;
            }
        }
        result.forEach(e ->{
            System.out.println(e);
        });
    }

    /**
     * 假设实现活动已经按照fi的升序排列好了的话，会发现实际上贪心算法在处理这个问题的时候只做了一次遍历，所以算法复杂度为O(n)。
     * @param activeList
     */
    private static void solveV2(List<Active> activeList) {
        Collections.sort(activeList,(a,b)-> a.end-b.end);
        List<Active> result = new ArrayList<>();
        Active choosed = activeList.get(0);
        result.add(choosed);
        for(int i=1;i<activeList.size();i++) {
            Active current = activeList.get(i);
            if(current.begin >= choosed.end) {
                choosed = current;
                result.add(choosed);
            }
        }
        result.forEach(e ->{
            System.out.println(e);
        });
    }

    static class Active {
        public int num;
        public int begin;
        public int end;

        public Active(int num, int begin, int end) {
            this.num = num;
            this.begin = begin;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Active{" +
                    "num=" + num +
                    ", begin=" + begin +
                    ", end=" + end +
                    '}';
        }
    }
}
