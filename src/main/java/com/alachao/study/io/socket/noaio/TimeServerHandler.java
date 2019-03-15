package com.alachao.study.io.socket.noaio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * @Author yuliang-ds1
 * @Date 2019/3/15 11:17
 * @Description
 */
public class TimeServerHandler  implements Runnable{

    private Socket socket;

    public  TimeServerHandler(Socket  socket){
        this.socket=socket;
    }

    @Override
    public void run() {

        BufferedReader bufferedReader=null;
        PrintWriter printWriter=null;

        System.out.println("Current  Thread Pool : "+Thread.currentThread().getName());

        try {
            bufferedReader=new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            printWriter=new PrintWriter(this.socket.getOutputStream());

            while(true){
               String readLine= bufferedReader.readLine();
               if(readLine==null)break;
               System.out.println("The time Server receive order:" +readLine);
               String serverResponse=readLine.equalsIgnoreCase("QUERY TIME ORDER")?new Date(System.currentTimeMillis()).toString():"QUERY  BAD  ORDER";
                printWriter.println(serverResponse);
                printWriter.flush();


            }

        } catch (IOException e) {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if(printWriter!=null){
                printWriter.close();
                printWriter=null;
            }

            if(this.socket!=null){
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                socket=null;
            }
        }


    }
}
