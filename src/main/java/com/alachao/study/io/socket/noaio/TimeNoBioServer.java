package com.alachao.study.io.socket.noaio;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.sql.Time;
import java.util.concurrent.*;

/**
 * @Author yuliang-ds1
 * @Date 2019/3/15 11:00
 * @Description
 */
public class TimeNoBioServer {

    public  static void main(String args[]){
        int port=8080;
        ServerSocket socketServer=null;
        java.net.Socket socket=null;
        try {
            socketServer=new ServerSocket(port);
            TimeServerHandlerThreadPool timeServerHandler=new TimeServerHandlerThreadPool(5,1);

            while(true){
                socket=socketServer.accept();
                TimeServerHandler  timeServerHandler1=new TimeServerHandler(socket);
                timeServerHandler.execute(timeServerHandler1);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
