package com.alachao.study.daemon;

/*
如果我们对某个线程对象在启动(调用start方法)之前调用了setDaemon(true)方法,这个线程就变成了
后台线程.对java程序来说,只要还有一个前台线程在运行,这个进程就不会结束,如果一个进程中只有
后台线程运行,这个进程会结束.
*/
public class QHThread{
    public static void main(String[]aregs)
    {
        Thread m=new MoreThread();
        m.setDaemon(true);//把"true"改为"false"就变为了前台线程.
        m.start();
        /*
        while(true)
        System.out.println("main方法-->>"+Thread.currentThread().getName());
        注释这两行输出结果:run方法-->>Thread-0,执行一会儿就结束了.
        如果不注释这两行输出结果是,main方法和run方法交替循环执行.
        */

    }
}

class MoreThread extends Thread
{
    public void run()
    {
        while(true)
            System.out.println("run方法-->>"+Thread.currentThread().getName());
    }
}