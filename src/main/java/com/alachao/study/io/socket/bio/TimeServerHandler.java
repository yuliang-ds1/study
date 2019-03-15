package com.alachao.study.io.socket.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * @Author yuliang-ds1
 * @Date 2019/3/14 20:57
 * @Description
 */
public class TimeServerHandler implements Runnable {


    private Socket  socket;

    public  TimeServerHandler(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {

        BufferedReader  in=null;
        PrintWriter printWriter=null;
        try {
            in=new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            printWriter=new PrintWriter(this.socket.getOutputStream(),true);;
            String  currentTime=null;
            String  body=null;
            while(true){

                body=in.readLine();
                if(body==null)break;
                System.out.println("The time Server receive order: "+body);
                currentTime="QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString() :"BAD ORDER";
                printWriter.println(currentTime);
            }

        } catch (IOException e) {

            if(in!=null){
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                in=null;
            }

            if(printWriter!=null){
                printWriter.close();
                printWriter=null;
            }

            if(this.socket!=null){
                try {
                    this.socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            this.socket=null;
        }

    }
}
