package com.alachao.study.thread.printTwo;

import com.alachao.study.thread.printTwo.MyService;

/**
 * @Author yuliang-ds1
 * @Date 14:09  2018/4/16.
 * @Desciption
 */
public class ThreadA extends Thread {

    private MyService  myService;

    public ThreadA(MyService myService){
       this.myService=myService;
    }

    @Override
    public void run(){
        for (int i=1;i<100;i=i+2){
            myService.printOddNumber(i);
            System.out.print(",");
        }
    }

    public MyService getMyService() {
        return myService;
    }

    public void setMyService(MyService myService) {
        this.myService = myService;
    }
}
