package com.alachao.study.io.socket.aio;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author yuliang-ds1
 * @Date 2019/3/18 11:56
 * @Description
 */
public class TimeAioClient {


    public  static void main(String args[]){

        int port=8080;

        AsynchoTimeClientHandler asynchoTimeClientHandler = new AsynchoTimeClientHandler("127.0.0.1", port);

        new Thread(asynchoTimeClientHandler,"TimeAioClient--001").start();
    }


}
