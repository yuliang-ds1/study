package com.alachao.study.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * 计数栅栏 CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，
 * 它才执行,子线程执行countDown之后，继续执行；
 * @Author yuliang-ds1
 * @Date 16:50  2018/4/16.
 * @Desciption
 */
public class TestCountDownLatchMain {


    public  static  Integer num=0;

    public static void main(String args[]) {


        while(num<10){
            CountDownLatch countDownLatch=new CountDownLatch(2);
            long startTime=System.currentTimeMillis();
            num=num+1;
            Thread a=new RunThreadA(countDownLatch,num);
            a.start();
            num=num+1;
            Thread b=new RunThreadB(countDownLatch,num);
            b.start();
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}

/*
准备起跑~~~
小马准备起跑~~~
小凯准备起跑~~~
起跑了~~~5001
*/
