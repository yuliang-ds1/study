package com.alachao.study.io.socket.aio;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author yuliang-ds1
 * @Date 2019/3/18 10:52
 * @Description
 */
public class TimeAioServer {


    public  static  void  main(String args[])throws Exception{

        int  port=8080;
        AsyncTimeServerHandler serverHandler=new AsyncTimeServerHandler(port);
        new Thread(serverHandler,"TimeAioServer-start-001").start();

    }



}
