package com.alachao.study.io.socket.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;

/**
 * @Author yuliang-ds1
 * @Date 2019/3/18 11:32
 * @Description
 */
public class ReadCompleteHandler implements CompletionHandler<Integer,ByteBuffer> {


    private AsynchronousSocketChannel channel;

    public ReadCompleteHandler(AsynchronousSocketChannel  channel){
        if(this.channel==null)
            this.channel=channel;
    }


    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        byte[]  body=new byte[attachment.remaining()];
        attachment.get(body);
        try {
            String req=new String(body,"UTF-8");
            System.out.println("The Time Server receive order: "+req);
            String currentTime="QUERY TIME ORDER".equalsIgnoreCase(req)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
            doWrite(currentTime);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void doWrite(String currentTime){
            if(currentTime!=null&&currentTime.trim().length()>0){
                byte[] bytes=currentTime.getBytes();
                final ByteBuffer writeBuffer=ByteBuffer.allocate(currentTime.length());
                writeBuffer.put(bytes);
                writeBuffer.flip();

                channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer attachment) {
                        //如果没有发送完成继续发送
                        if(writeBuffer.hasRemaining()){
                            channel.write(writeBuffer,writeBuffer,this);
                        }
                    }
                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {

                        try {
                            channel.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                });

            }


    }
}
