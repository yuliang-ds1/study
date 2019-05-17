package com.alachao.study.thread.add;

/**
 * @Author yuliang-ds1
 * @Date 2019/4/23 15:41
 * @Description
 */
public class ThreadAdd extends Thread {

    private  AddService addService;

    public ThreadAdd(AddService addService){
        this.addService=addService;
    }




    @Override
    public void run() {
        for (int i=0;i<100;i++)addService.add();
        System.out.println("name:"+Thread.currentThread()+"    num:"+addService.getNum());
    }
}
