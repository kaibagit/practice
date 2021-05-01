//package me.luliru.parctice.algorithm.bit;
//
///**
// * LC393_UTF8编码验证
// * Created by luliru on 2021/3/6.
// */
//public class LC393_UTF8编码验证 {
//
//    public boolean validUtf8(int[] data) {
//        if (data.length == 0) {
//            return true;
//        }
//
//        int index = 0;
//        while (index < data.length) {
//            byte first = (byte) data[index];
//            int length = countCharLength(first);
//            if (false) {     // 长度不够用
//                return false;
//            }
//            for (int i = 1; i <= length - 1; i++) {
//                ++index;
//                if (!validateFollow((byte) data[index])) {
//                    return false;
//                }
//            }
//        }
//    }
//
//    private int countCharLength(byte first) {
//        if (first >= 0) {
//            return 1;
//        }
//        int length = 0;
//        while ((first & -1) != 0) {
//            ++length;
//            first = (byte) (first << 1);
//        }
//        return length;
//    }
//
//    private boolean validateFollow(byte follow) {
//        // follow & 11000000 == 10000000
//        return true;
//    }
//}
