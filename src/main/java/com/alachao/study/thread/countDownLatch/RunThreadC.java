package com.alachao.study.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class RunThreadC extends Thread {


    private CountDownLatch countDownLatch;

    public RunThreadC(CountDownLatch countDownLatch){
        this.countDownLatch=countDownLatch;
    }

    @Override
    public  void  run(){
        System.out.println("砸一下~~~");
        try {
            Thread.sleep(5000);
        }catch(InterruptedException exception){
            exception.printStackTrace();
        }
        countDownLatch.countDown();
    }


}
