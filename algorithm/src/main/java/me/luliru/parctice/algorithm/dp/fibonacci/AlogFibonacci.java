package me.luliru.parctice.algorithm.dp.fibonacci;

//斐波那契数列应该是:0,1,1,2,3,5,8,13,21,34,55,89,144,233,...
//规律是:f[0]=0,f[1]=1,f[i]=f[i-1]+f[i-2],i>1

public class AlogFibonacci {
    public static void main(String[] args) {
        int n=40;
        long array[]=new long [n+1];
        array[0]=0;
        array[1]=1;
        long startTime=System.currentTimeMillis();
        for(int i=2;i<n+1;i++){
            array[i]=array[i-1]+array[i-2];
        }
        for(int i=1;i<n+1;i++){
            System.out.print(array[i]+"  ");
        }
        System.out.println("\ntime:"+(System.currentTimeMillis()-startTime));
    }
}