package com.alachao.study.thread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * http://www.importnew.com/25286.html
 * 获取异步线程执行结果
 * @Author yuliang-ds1
 * @Date 18:40  2018/4/16.
 * @Desciption
 */
public class TestFuture {

    public static void main(String args[]) throws ExecutionException, InterruptedException {

        // 1. 新建FutureTask,需要一个实现了Callable接口的类的实例作为构造函数参数
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Task());
        // 2. 新建Thread对象并启动
        Thread thread = new Thread(futureTask);
        thread.setName("Task thread");
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread [" + Thread.currentThread().getName() + "] is running");

        // 3. 调用isDone()判断任务是否结束
        if (!futureTask.isDone()) {
            System.out.println("Task is not done");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        int result = 0;
        try {
            // 4. 调用get()方法获取任务结果,如果任务没有执行完成则阻塞等待
            result = futureTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("result is " + result);

    }

    // 1. 继承Callable接口,实现call()方法,泛型参数为要返回的类型
    static class Task implements Callable<Integer>{
        @Override
        public Integer call () throws Exception {
            System.out.println("Thread [" + Thread.currentThread().getName() + "] is running");
            int result = 0;
            for (int i = 0; i < 100; ++i) {
                result += i;
            }
            Thread.sleep(3000);
            return result;
      }
  }
}
