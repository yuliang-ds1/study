package com.alachao.study.thread.printThree;

/**
 * @Author yuliang-ds1
 * @Date 14:17  2018/4/16.
 * @Desciption
 */
public class TestPrintThread {

    /**
     * 主方法
     * @param args
     */
    public static void main(String args[]){
        MyService myService=new MyService();
        Thread a=new ThreadA(myService);
        Thread b=new ThreadB(myService);
        a.start();
        b.start();
    }
}
