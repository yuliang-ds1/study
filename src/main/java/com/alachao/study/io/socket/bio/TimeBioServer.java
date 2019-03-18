package com.alachao.study.io.socket.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author yuliang-ds1
 * @Date 2019/3/14 20:48
 * @Description
 */
public class TimeBioServer {


    public  static  void  main(String args[]) throws IOException {
        int  port=8080;
        if(args!=null&&args.length>0){

            try{
                port=Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                //采用默认值
            }
        }

        ServerSocket server=null;

        try{
            server=new ServerSocket(port);
            System.out.println("The Time Server is start in port: "+port);
            Socket  socket=null;
            while(true){
                socket=server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }

        }finally {

            if(server!=null){
                System.out.println("The time server close ");
                server.close();
                server=null;
            }

        }

    }


}
