package com.alachao.study.thread.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 字面意思回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
 * 叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。
 * 我们暂且把这个状态就叫做barrier，当调用await()方法之后，线程就处于barrier了。
 * @Author yuliang-ds1
 * @Date 17:23  2018/4/16.
 * @Desciption
 */
public class TestCyclicBarrier {

    public static void main(String args[]){

        CyclicBarrier cyclicBarrier=new CyclicBarrier(2,new Runnable(){
            @Override
            public void run(){
                //等待其他任务过程中，先执行完的空闲线程，执行此线程任务；
                System.out.println("当前线程"+Thread.currentThread().getName()+" 当前时间："+System.currentTimeMillis());
            }
        });
       /* for(int i=0;i<2;i++){
           new CyclicBarrierThreadA(cyclicBarrier).start();
        }*/
        new CyclicBarrierThreadA(cyclicBarrier).start();
        new CyclicBarrierThreadB(cyclicBarrier).start();

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("中餐");
        //第一次重复执行任务
        /*for(int i=0;i<2;i++){
            new CyclicBarrierThreadA(cyclicBarrier).start();
        }*/
        new CyclicBarrierThreadA(cyclicBarrier).start();
        new CyclicBarrierThreadB(cyclicBarrier).start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("晚餐");
        //第二次重复执行任务
        for(int i=0;i<2;i++){
            new CyclicBarrierThreadA(cyclicBarrier).start();
        }

    }

}

/* 执行结果
线程Thread-0正在写入数据...
线程Thread-1正在写入数据...
线程Thread-0写入数据完毕，等待其他线程写入完毕
线程Thread-1写入数据完毕，等待其他线程写入完毕
当前线程Thread-1
所有线程写入完毕，继续处理其他任务...
所有线程写入完毕，继续处理其他任务...
中餐
线程Thread-2正在写入数据...
线程Thread-3正在写入数据...
线程Thread-3写入数据完毕，等待其他线程写入完毕
线程Thread-2写入数据完毕，等待其他线程写入完毕
当前线程Thread-2
所有线程写入完毕，继续处理其他任务...
所有线程写入完毕，继续处理其他任务...
晚餐
线程Thread-4正在写入数据...
线程Thread-5正在写入数据...
线程Thread-4写入数据完毕，等待其他线程写入完毕
线程Thread-5写入数据完毕，等待其他线程写入完毕
当前线程Thread-5
所有线程写入完毕，继续处理其他任务...
所有线程写入完毕，继续处理其他任务...
*/


class CyclicBarrierThreadA extends  Thread {

    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierThreadA(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier=cyclicBarrier;
    }

    @Override
    public void run(){
        System.out.println("线程A"+Thread.currentThread().getName()+"正在写入数据..."+" 当前时间："+System.currentTimeMillis());
        try {
            Thread.sleep(5000);
            System.out.println("线程A"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕"+" 当前时间："+System.currentTimeMillis());
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("线程A"+Thread.currentThread().getName()+"所有线程写入完毕，继续处理其他任务..."+"当前时间："+System.currentTimeMillis());

    }


}



class CyclicBarrierThreadB extends  Thread {

    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierThreadB(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier=cyclicBarrier;
    }

    @Override
    public void run(){
        System.out.println("线程B"+Thread.currentThread().getName()+"正在写入数据..."+" 当前时间："+System.currentTimeMillis());
        try {
            Thread.sleep(10000);
            System.out.println("线程B"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕"+" 当前时间："+System.currentTimeMillis());
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("线程B"+Thread.currentThread().getName()+"所有线程写入完毕，继续处理其他任务..."+"当前时间："+System.currentTimeMillis());

    }


}