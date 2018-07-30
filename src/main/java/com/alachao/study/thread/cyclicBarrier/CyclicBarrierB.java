package com.alachao.study.thread.cyclicBarrier;

import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierB extends Thread {

    private CyclicBarrier cyclicBarrier;

    public  CyclicBarrierB(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier=cyclicBarrier;
    }

    @Override
    public  void  run(){
        System.out.println("B砸一下~~~");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("B喘口气");

    }


}
