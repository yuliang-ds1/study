package com.alachao.study.thread.test;

public class Main {


    public static void main(String [] args){
        MyService myService=new MyService();
        Thread a=new ThreadA(myService);
        Thread b=new ThreadB(myService);
        a.start();
        b.start();

    }

}
