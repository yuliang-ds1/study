package com.alachao.study.thread.test;

public class ThreadB extends Thread {

    private MyService myservice;

    public ThreadB(MyService myService){
        this.myservice=myService;
    }

    @Override
    public  void run(){
        for (int i=0;i<100;i++) {
            myservice.pringShuangshu();
        }
    }
}
