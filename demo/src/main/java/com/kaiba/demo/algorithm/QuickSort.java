package com.kaiba.demo.algorithm;

/**
 * Created by kaiba on 2016/8/28.
 */
public class QuickSort {
    static void quick_sort(int s[], int low, int high) {
        int i, j, x;
        if (low < high) {
            i = low;
            j = high;
            x = s[i];
            while (i < j) {
                while (i < j && x < s[j])
                    j--; /* 从右向左找第一个小于x的数 */
                if (i < j) {
                    s[i] = s[j];
                    i++;
                }

                while (i < j && s[i] < x)
                    i++; /* 从左向右找第一个大于x的数 */
                if (i < j) {
                    s[j] = s[i];
                    j--;
                }

            }
            s[i] = x;
            quick_sort(s, low, i - 1); /* 递归调用 */
            quick_sort(s, i + 1, high);
        }
    }

    public static void main(String[] args) {
        int a[] = {49, 38, 65, 97, 76, 13, 27};
        int l = 0;
        int r = a.length-1; //最后一个索引位置
        quick_sort(a, l, r);

        for (int i = 0; i <= r; i++)
            System.out.printf("%4d", a[i]);
    }


}
