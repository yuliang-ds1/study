package com.alachao.study.thread.test;

public class ThreadA  extends Thread {

    private MyService myservice;

    public ThreadA(MyService myService){
        this.myservice=myService;
    }

    @Override
    public  void run(){
        for (int i=0;i<100;i++) {
            myservice.printDanShu();
        }
    }
}
