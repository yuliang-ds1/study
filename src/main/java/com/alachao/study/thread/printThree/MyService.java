package com.alachao.study.thread.printThree;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题两个线程交替打印
 * 线程一打印： 1,3,5,7,9.....99;
 * 线程二打印： 2,4,6,8,10,...100;
 * 最终显示: 1,2,3,4,5,6,7....100;
 * @Author yuliang-ds1
 * @Date 13:51  2018/4/16.
 * @Desciption
 */
public class MyService {

    public MyService(){}
    //重入锁
    //消息路由
    private volatile  boolean flag=true;

    /**
     * 线程1打印奇数
     */
    synchronized public void printOddNumber(int num){
        //1.锁定资源
        //2.如果当前应该打印偶数，此方法阻塞等待
        if(!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(num);
        System.out.print(",");
        flag=false;
        this.notifyAll();

    }

    /**
     * 线程2打印偶数
     */
    synchronized public void  printEvenNumber(int num){
        if(flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(num);
        System.out.print(",");
        flag=true;
        this.notifyAll();
    }


}
