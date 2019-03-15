package com.alachao.study.io.socket.bio;

import java.io.*;
import java.net.Socket;

/**
 * @Author yuliang-ds1
 * @Date 2019/3/15 10:18
 * @Description
 */
public class TimeClient {

    public static void main(String args[]){

        int port=8080;
        if(args!=null&&args.length>0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){

            }
        }


        Socket  socket=null;
        BufferedReader bufferedReader=null;
        PrintWriter printWriter=null;
        try {
            socket=new Socket("127.0.0.1",port);
            bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter=new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("QUERY TIME ORDER");
            System.out.println("Send order 2 server Succed. ");
            String response=bufferedReader.readLine();
            System.out.println("Now is: "+response);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(printWriter!=null){
                printWriter.close();
                printWriter=null;
            }
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bufferedReader=null;
            }
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket=null;
            }
        }

    }

}
