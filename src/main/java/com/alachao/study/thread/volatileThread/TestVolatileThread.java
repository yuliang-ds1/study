package com.alachao.study.thread.volatileThread;

import com.alachao.study.thread.volatileThread.Myservice;

/**
 * @Author yuliang-ds1
 * @Date 15:57  2018/4/16.
 * @Desciption
 */
public class TestVolatileThread {

    /**
     * 主方法
     * @param args
     */
    public static void main(String args[]){
        Myservice myService=new Myservice();
        Thread a=new ThreadA(myService);
        a.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread b=new ThreadB(myService);
        b.start();
    }
}
