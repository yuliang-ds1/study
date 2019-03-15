package com.alachao.study.io.socket.noaio;

import java.util.concurrent.*;

/**
 * @Author yuliang-ds1
 * @Date 2019/3/15 11:29
 * @Description
 */
public class TimeServerHandlerThreadPool {

    private  int  coreSize;

    private  int  queueSize;

    private ExecutorService executorService;

    public  TimeServerHandlerThreadPool(int coreSize,int queueSize){

        this.coreSize=coreSize;
        this.queueSize=queueSize;

        RejectedExecutionHandler  rejectedExecutionHandler= new ThreadPoolExecutor.AbortPolicy();
        executorService=new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),coreSize,10l, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(queueSize),rejectedExecutionHandler);

    }

    public void execute(Runnable runnable){
        executorService.execute(runnable);
    }


}
