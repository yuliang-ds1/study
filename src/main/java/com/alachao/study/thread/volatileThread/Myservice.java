package com.alachao.study.thread.volatileThread;

/**
 * @Author yuliang-ds1
 * @Date 14:27  2018/4/18.
 * @Desciption
 */
public class Myservice {

    public volatile boolean  flag=true;

    public void  runMethod(){
        while(flag){
            System.out.println("死循环");
        }
        System.out.println("停下来了");
    }

    public  void stopMethod(){
        this.flag=false;
    }
}
