package com.alachao.study.mydefine;

public class StepHouse {



    public static void main(String args[]){

        int step = step(7, 2);
        System.out.println(step);


    }


    public static int step(int n,int m ){
        if(n-m<0){
            return 1;
        }
        int sum=0;
        for (int i=1;i<=m;i++){
            sum=sum+step(n-i,m);
        }
        return  sum;
    }


}



