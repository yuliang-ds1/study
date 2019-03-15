package com.alachao.study.io.socket.nio;

import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author yuliang-ds1
 * @Date 2019/3/15 15:00
 * @Description
 */
public class TimeServer {

    public static  void main(String  args[]){

        int port=8080;

        MultiplexerTimeServer  multiplexerTimeServer=new MultiplexerTimeServer(port);

        new Thread(multiplexerTimeServer,"NIO-multiplexerTimeServer-001").start();



    }
}
