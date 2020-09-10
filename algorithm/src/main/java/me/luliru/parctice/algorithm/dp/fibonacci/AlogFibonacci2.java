package me.luliru.parctice.algorithm.dp.fibonacci;

//斐波那契数列应该是:0,1,1,2,3,5,8,13,21,34,55,89,144,233,...
//规律是:f[0]=0,f[1]=1,f[i]=f[i-1]+f[i-2],i>1

/**
 * AlogFibonacci2
 * Created by luliru on 2020-05-26.
 */
public class AlogFibonacci2 {
    public static void main(String[] args) {
        int n=40;
        long startTime=System.currentTimeMillis();
        System.out.println(fibonacci(n));
        System.out.println("\ntime:"+(System.currentTimeMillis()-startTime));
    }
    static int fibonacci(int i){
        if(i==0){
            return 0;
        }else if(i==1){
            return 1;
        }else {
            return fibonacci(i-1)+fibonacci(i-2);
        }
    }
}
