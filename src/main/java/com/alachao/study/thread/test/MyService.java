package com.alachao.study.thread.test;


/**
 *
 */
public class MyService {

    private Integer num=1;

    public  MyService(){

    }

    synchronized public void printDanShu(){

        String name=Thread.currentThread().getName();
        System.out.println(name+"   num:"+num);
        if(num%2==1){
            System.out.print(num);
            System.out.print(",");
            System.out.println();
            num++;
        }else{
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.notifyAll();


    }

    synchronized public void pringShuangshu(){
        String name=Thread.currentThread().getName();
        System.out.println(name+"   num:"+num);

        if(num==100){
            System.out.print(num);
            num++;
        }else if(num%2==0){
            System.out.print(num);
            System.out.print(",");
            System.out.println();
            num++;
        }else{
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();


    }

}
