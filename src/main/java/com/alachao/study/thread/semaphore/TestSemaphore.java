package com.alachao.study.thread.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Semaphore可以控同时访问的线程个数
 * @Author yuliang-ds1
 * @Date 17:55  2018/4/16.
 * @Desciption
 */
public class TestSemaphore {

    public static void main(String args[]){

        Semaphore semaphore=new Semaphore(2);

        for(int i=0;i<10;i++){
            new SemaphoreThread(semaphore).start();
        }
    }
}

class  SemaphoreThread extends Thread{

    private  Semaphore semphore;

    SemaphoreThread(Semaphore semphore) {
        this.semphore = semphore;
    }

    @Override
    public void run(){
        try {
            semphore.acquire();
            System.out.println("线程"+Thread.currentThread()+"在干活");
            Thread.sleep(2000);
            System.out.println("线程"+Thread.currentThread()+"下班");
            semphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

/*
线程Thread[Thread-0,5,main]在干活
线程Thread[Thread-2,5,main]在干活
线程Thread[Thread-2,5,main]下班
线程Thread[Thread-0,5,main]下班
线程Thread[Thread-4,5,main]在干活
线程Thread[Thread-1,5,main]在干活
线程Thread[Thread-1,5,main]下班
线程Thread[Thread-3,5,main]在干活
线程Thread[Thread-4,5,main]下班
线程Thread[Thread-5,5,main]在干活
线程Thread[Thread-3,5,main]下班
线程Thread[Thread-5,5,main]下班
线程Thread[Thread-6,5,main]在干活
线程Thread[Thread-7,5,main]在干活
线程Thread[Thread-7,5,main]下班
线程Thread[Thread-6,5,main]下班
线程Thread[Thread-8,5,main]在干活
线程Thread[Thread-9,5,main]在干活
线程Thread[Thread-9,5,main]下班
线程Thread[Thread-8,5,main]下班

*/


