package com.alachao.study.thread.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author yuliang-ds1
 * @Date 2019/2/27 15:18
 * @Description
 */
public class FutureTaskForMultiCompute {


    /**
     * 多线程计算任务
     * @param args
     */
    public  static void main(String args[]){

        List<FutureTask<Integer>> list=new ArrayList<FutureTask<Integer>>();

        ExecutorService executorService= Executors.newFixedThreadPool(5);

        FutureTaskForMultiCompute  futureTaskForMultiCompute=new FutureTaskForMultiCompute();


        for(int i=0;i<10;i++){
            FutureTask<Integer> futureTask=new FutureTask<Integer>(futureTaskForMultiCompute.new ComputeTask(i,"线程-"+i));
            list.add(futureTask);
            executorService.submit(futureTask);

        }
        System.out.println("所有计算任务提交完毕, 主线程接着干其他事情！");

        Integer totalResult=0;

        for (FutureTask<Integer> fut: list) {

            try {
                Integer result=fut.get();
                totalResult+=result;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        }


        executorService.shutdown();
        System.out.println("所有计算任务计算完毕, 结果！"+totalResult);


    }





    class  ComputeTask implements Callable<Integer>{

        private Integer initResult=0;

        private String  taskName;

        public ComputeTask(Integer initResult,String taskName){
            this.initResult=initResult;
            this.taskName=taskName;
            System.out.println("生成子线程计算任务: "+taskName);
        }

        public String getTaskName() {
            return taskName;
        }

        @Override
        public Integer call() throws Exception {
            for (int i=0;i<100;i++) {
                initResult+=i;
            }
            // 休眠5秒钟，观察主线程行为，预期的结果是主线程会继续执行，到要取得FutureTask的结果是等待直至完成。
            Thread.sleep(5000);
            System.out.println("子线程计算任务: "+taskName+" result:"+initResult+" 执行完成!");
            return initResult;
        }
    }


}
