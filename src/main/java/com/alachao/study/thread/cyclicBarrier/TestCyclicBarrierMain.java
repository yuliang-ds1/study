package com.alachao.study.thread.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrierMain {

    public static  void  main(String args[]){

        CyclicBarrier cyclicBarrier=new CyclicBarrier(2,new Thread(){
            @Override
            public void run(){
                 System.out.println("Next~~~");
            }
        });


        while(true){
            new CyclicBarrierA(cyclicBarrier).start();
            new CyclicBarrierB(cyclicBarrier).start();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
