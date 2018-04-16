package com.alachao.study.thread.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 两个线程之间进行数据交换,线程会阻塞在Exchanger的exchange方法上，
 * 直到另外一个线程也到达Exchanger的exchanger方法，进行数据交互；
 * @Author yuliang-ds1
 * @Date 18:06  2018/4/16.
 * @Desciption
 */
public class TestExchanger {

    public static void main(String args[]){
        Exchanger<List<Integer>>  listExchanger=new Exchanger<List<Integer>>();
        Thread a=new ThreadA(listExchanger);
        a.start();

        Thread b=new ThreadB(listExchanger);
        b.start();
    }
}
/*执行结果
线程：Thread[Thread-0,5,main]   [1, 2]
线程：Thread[Thread-1,5,main]   [4, 5]
线程：Thread[Thread-1,5,main][1, 2]
线程：Thread[Thread-0,5,main][4, 5]
*/


class ThreadA extends Thread{

    private  Exchanger<List<Integer>>  listExchanger;

    public ThreadA(Exchanger<List<Integer>> listExchanger){
        this.listExchanger=listExchanger;
    }

    @Override
    public void run(){
        List<Integer>  list=new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        System.out.println("线程："+Thread.currentThread()+"   "+list);
        try {
            list=listExchanger.exchange(list);
            System.out.println("线程："+Thread.currentThread()+list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}



class ThreadB extends Thread{

    private  Exchanger<List<Integer>>  listExchanger;

    public ThreadB(Exchanger<List<Integer>> listExchanger){
        this.listExchanger=listExchanger;
    }

    @Override
    public void run(){
        List<Integer>  list=new ArrayList<Integer>();
        list.add(4);
        list.add(5);
        System.out.println("线程："+Thread.currentThread()+"   "+list);
        try {
            list=listExchanger.exchange(list);
            System.out.println("线程："+Thread.currentThread()+list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
