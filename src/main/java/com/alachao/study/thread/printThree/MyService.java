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
    //全局标识用于交互打印
    Integer index=1;

    /**
     * 线程1打印奇数
     */
    synchronized public void printOddNumber(int num){
        //1.锁定资源
        //2.如果当前应该打印偶数，此方法阻塞等待
        if(index%2==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //3.如果当前应该打印奇数，打印奇数，并唤醒阻塞线程；
        index=index+1;
        System.out.print(num);
        System.out.print(",");
        this.notifyAll();

    }

    /**
     * 线程2打印偶数
     */
    synchronized public void  printEvenNumber(int num){
        if(index%2==1){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        index=index+1;
        System.out.print(num);
        System.out.print(",");
        this.notifyAll();
    }


}
