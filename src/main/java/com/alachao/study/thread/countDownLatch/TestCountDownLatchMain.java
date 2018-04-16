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

    public static void main(String args[]){

        CountDownLatch countDownLatch=new CountDownLatch(2);
        try {
            long startTime=System.currentTimeMillis();
            System.out.println("准备起跑~~~");
            Thread a=new RunThreadA(countDownLatch);
            a.start();
            Thread b=new RunThreadB(countDownLatch);
            b.start();
            Thread.sleep(5000);
            //等待两个线程执行完毕才会到这里
            countDownLatch.await();
            long endTime=System.currentTimeMillis();
            System.out.println("起跑了~~~"+(endTime-startTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

/*
准备起跑~~~
小马准备起跑~~~
小凯准备起跑~~~
起跑了~~~5001
*/
