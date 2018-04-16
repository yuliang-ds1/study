package com.alachao.study.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Author yuliang-ds1
 * @Date 16:52  2018/4/16.
 * @Desciption
 */
public class RunThreadA extends Thread{

    private CountDownLatch countDownLatch;

    public RunThreadA(CountDownLatch countDownLatch){
        this.countDownLatch=countDownLatch;
    }

    @Override
    public void run(){
        System.out.println("小马准备起跑~~~");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
    }


}
