package com.alachao.study.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Author yuliang-ds1
 * @Date 16:52  2018/4/16.
 * @Desciption
 */
public class RunThreadB extends Thread{

    private CountDownLatch countDownLatch;
    private Integer num ;

    public RunThreadB(CountDownLatch countDownLatch,Integer num){
        this.countDownLatch=countDownLatch;
        this.num=num;
    }

    @Override
    public void run(){
        if(num<10){
            System.out.println("threadName: +"+Thread.currentThread().getName()+"   "+num+",");
        }else{
            System.out.print(num);
        }
        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        countDownLatch.countDown();

        System.out.println("threadName: +"+Thread.currentThread().getName()+"  going ");
    }


}
