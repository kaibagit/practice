package me.luliru.parctice.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典序排数
 * Created by luliru on 2020/10/17.
 */
public class 字典序排数 {

    private List<Integer> res = new ArrayList<>();

    public List<Integer> lexicalOrder(int n) {
        backtrack(0,1,n);
        return res;
    }

    public void backtrack(int path,int level,int n) {
        for(int i=0;i<=9;i++) {
            if(level == 1 && i == 0) {
                continue;
            }

            //做选择
            int val = path * 10 + i;
            if(val <= n) {
                res.add(val);
            } else {
                return;
            }
            // 深度遍历
            backtrack(val,level+1,n);
            //撤销选择
        }
    }
}
