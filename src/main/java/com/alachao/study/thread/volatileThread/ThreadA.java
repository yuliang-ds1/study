package com.alachao.study.thread.volatileThread;

import com.alachao.study.thread.volatileThread.Myservice;

/**
 * @Author yuliang-ds1
 * @Date 14:09  2018/4/16.
 * @Desciption
 */
public class ThreadA extends Thread {

    private Myservice  myService;

    public ThreadA(Myservice myService){
       this.myService=myService;
    }

    @Override
    public void run(){
            myService.runMethod();
    }

    public Myservice getMyService() {
        return myService;
    }

    public void setMyService(Myservice myService) {
        this.myService = myService;
    }
}
