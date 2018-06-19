package com.alachao.study.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class RunThreadC extends Thread {


    private CountDownLatch countDownLatch;

    public RunThreadC(CountDownLatch countDownLatch){
        this.countDownLatch=countDownLatch;
    }

    @Override
    public  void  run(){
        try {
            Thread.sleep(13000);
        }catch(InterruptedException exception){
            exception.printStackTrace();
        }
        System.out.println("我正在吃C");
        countDownLatch.countDown();
    }


}
