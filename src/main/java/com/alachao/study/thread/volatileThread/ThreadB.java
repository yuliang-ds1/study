package com.alachao.study.thread.volatileThread;

/**
 * @Author yuliang-ds1
 * @Date 14:09  2018/4/16.
 * @Desciption
 */
public class ThreadB extends Thread {

    private Myservice  myService;

    public ThreadB(Myservice myService){
       this.myService=myService;
    }

    @Override
    public void run(){
            myService.stopMethod();
    }

    public Myservice getMyService() {
        return myService;
    }

    public void setMyService(Myservice myService) {
        this.myService = myService;
    }
}
