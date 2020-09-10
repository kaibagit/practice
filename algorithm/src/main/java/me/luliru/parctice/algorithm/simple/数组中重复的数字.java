package me.luliru.parctice.algorithm.simple;

/**
 * 数组中重复的数字:
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 * https://www.nowcoder.com/practice/623a5ac0ea5b4e5f95552655361ae0a8?tpId=13&tqId=11203&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking&from=cyc_github
 */
public class 数组中重复的数字 {

    public static void main(String[] args) {
//        int[] arr = {2, 3, 1, 0, 2, 5};
//        int result = find_dup(arr,6);
//        System.out.println(result);
        mine();
    }

    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    public static boolean duplicate(int numbers[],int length,int [] duplication) {
        return false;
    }

    /**
     *  不需要额外的数组或者hash table来保存，题目里写了数组里数字的范围保证在0 ~ n-1 之间，所以可以利用现有数组设置标志，当一个数字被访问过后，可以设置对应位上的数 + n，之后再遇到相同的数时，会发现对应位上的数已经大于等于n了，那么直接返回这个数即可。
     *  用减去length比较好，不会溢出
     * @param numbers
     * @param length
     * @return
     */
    public static int find_dup( int numbers[], int length) {
        for ( int i= 0 ; i<length; i++) {
            int index = numbers[i];
            if (index >= length) {
                index -= length;
            }
            if (numbers[index] >= length) {
                return index;
            }
            numbers[index] = numbers[index] + length;
        }
        return - 1 ;

    }

    public static void mine(){
        int[] arr = {2, 3, 1, 0, 2, 5};
        for(int i=0;i<arr.length;i++){
            int val = arr[i];
            if(val < 0) {
                val = val + arr.length;
            }
            if(arr[val] < 0){
                System.out.println(val);
                return;
            }
            arr[val] = arr[val] - arr.length;
        }
    }

}
