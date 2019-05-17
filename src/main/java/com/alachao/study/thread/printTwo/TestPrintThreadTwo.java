package com.alachao.study.thread.printTwo;

import com.alachao.study.thread.printTwo.MyService;
import com.alachao.study.thread.printTwo.ThreadA;
import com.alachao.study.thread.printTwo.ThreadB;

/**
 * @Author yuliang-ds1
 * @Date 15:57  2018/4/16.
 * @Desciption
 */
public class        TestPrintThreadTwo {

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
