package me.luliru.parctice.algorithm.dp;

/**
 * LCP19秋叶收藏集
 * Created by luliru on 2020/10/15.
 */
public class LCP19秋叶收藏集 {

    public static void main(String[] args) {
        new LCP19秋叶收藏集().minimumOperations("yry");
    }

    public int minimumOperations(String leaves) {
        int[][] records = new int[3][leaves.length()];
        // 处理全r的情况
        int lastCount = 0;
        for (int i=0;i<leaves.length();i++) {
            char c = leaves.charAt(i);
            if(c == 'r'){
                records[0][i] = lastCount;
            }else {
                records[0][i] = lastCount + 1;
            }
            lastCount = records[0][i];
        }
        // 处理ry的情况
        records[1][0] = leaves.charAt(0)=='r'?0:1;  // 头一个字母必须是 r
        for (int i=1;i<leaves.length();i++) {
            char c = leaves.charAt(i);
            if(c == 'r'){
                records[1][i] = Math.min(records[0][i-1],records[1][i-1]) + 1;
            }else {
                records[1][i] = Math.min(records[0][i-1],records[1][i-1]);
            }
        }
        // 处理ryr的情况
        records[2][0] = records[1][0];
        records[2][1] = records[2][0] + (leaves.charAt(1)=='y'?0:1);; //头2个字母必须是ry
        for (int i=2;i<leaves.length();i++) {
            char c = leaves.charAt(i);
            if(c == 'r'){
                records[2][i] = Math.min(records[1][i-1],records[2][i-1]);
            }else {
                records[2][i] = Math.min(records[1][i-1],records[2][i-1]) + 1;
            }
        }
        return records[2][leaves.length()-1];
    }

//    public int minimumOperations(String leaves) {
//        int[][] records = new int[leaves.length()][3];
//        // 处理全r的情况
//        int lastCount = 0;
//        for (int i=0;i<leaves.length();i++) {
//            char c = leaves.charAt(i);
//            if(c == 'r'){
//                records[i][0] = lastCount;
//            }else {
//                records[i][0] = lastCount + 1;
//            }
//            lastCount = records[i][0];
//        }
//        // 处理ry的情况
//        records[0][1] = leaves.charAt(0)=='r'?0:1;  // 头一个字母必须是 r
//        for (int i=1;i<leaves.length();i++) {
//            char c = leaves.charAt(i);
//            if(c == 'r'){
//                records[i][1] = Math.min(records[i-1][0],records[i-1][1]) + 1;
//            }else {
//                records[i][1] = Math.min(records[i-1][0],records[i-1][1]);
//            }
//        }
//        // 处理ryr的情况
//        records[0][2] = records[0][1];
//        records[1][2] = records[0][2] + leaves.charAt(1)=='r'?1:0;; //头2个字母必须是ry
//        for (int i=3;i<leaves.length();i++) {
//            char c = leaves.charAt(i);
//            if(c == 'r'){
//                records[i][2] = Math.min(records[i-1][1],records[i-1][2]);
//            }else {
//                records[i][2] = Math.min(records[i-1][1],records[i-1][2]) + 1;
//            }
//        }
//        return records[leaves.length()-1][2];
//    }
}
