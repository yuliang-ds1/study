package com.alachao.study.thread.printTwo;

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
    ReentrantLock lock=new ReentrantLock();
    //消息路由
    Condition condition=lock.newCondition();
    //全局标识用于交互打印
    Integer index=1;


    /**
     * 线程1打印奇数
     */
    public void printOddNumber(int num){
        //1.锁定资源
        lock.lock();
        try {
            //2.如果当前应该打印偶数，此方法阻塞等待
            if(index%2==0){
               condition.await();
            }
            //3.如果当前应该打印奇数，打印奇数，并唤醒阻塞线程；
            index=index+1;
            System.out.print(num);
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //4.释放资源
            lock.unlock();
        }

    }

    /**
     * 线程2打印偶数
     */
    public void  printEvenNumber(int num){
        lock.lock();
        try {
            if(index%2==1){
                condition.await();
            }
            index=index+1;
            System.out.print(num);
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


}
