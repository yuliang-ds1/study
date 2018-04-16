package com.alachao.study.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Author yuliang-ds1
 * @Date 16:52  2018/4/16.
 * @Desciption
 */
public class RunThreadB extends Thread{

    private CountDownLatch countDownLatch;

    public RunThreadB(CountDownLatch countDownLatch){
        this.countDownLatch=countDownLatch;
    }

    @Override
    public void run(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("小凯准备起跑~~~");
        countDownLatch.countDown();
    }


}
