package com.alachao.study.thread.printOne;

/**
 * @Author yuliang-ds1
 * @Date 14:09  2018/4/16.
 * @Desciption
 */
public class ThreadB extends Thread {

    private MyService  myService;

    public ThreadB(MyService myService){
       this.myService=myService;
    }

    @Override
    public void run(){
        for (int i=2;i<=100;i=i+2){
            myService.printEvenNumber(i);
        }
    }

    public MyService getMyService() {
        return myService;
    }

    public void setMyService(MyService myService) {
        this.myService = myService;
    }
}
