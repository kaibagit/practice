package me.luliru.parctice.algorithm;

/**
 * PrintTool
 * Created by luliru on 2022/5/9.
 */
public class PrintTool {

    public static void printArray(int[] arr){
        if (arr != null) {
            System.out.print("[");
            for(int x = 0 ;x <arr.length; x++){
                if(x==arr.length-1){
                    System.out.println(arr[x]+"]");
                }else{
                    System.out.print(arr[x]+", ");
                }
            }
        } else {
            System.out.println("null");
        }
    }
}
