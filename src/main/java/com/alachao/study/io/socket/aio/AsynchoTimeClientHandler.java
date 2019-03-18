package com.alachao.study.io.socket.aio;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * @Author yuliang-ds1
 * @Date 2019/3/18 11:58
 * @Description
 */
public class AsynchoTimeClientHandler implements CompletionHandler<Void,AsynchoTimeClientHandler>,Runnable {


    private AsynchronousSocketChannel client;
    private String host;
    private int  port;
    private CountDownLatch latch;


    /**
     * 1.构造方法，初始化异步连接对象
     * @param host
     * @param port
     */
    public  AsynchoTimeClientHandler(String host,int port){
        this.host=host;
        this.port=port;
        try {
            client=AsynchronousSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     *  1.latch等到，防止异步操作没有执行完成就瑞出
     */
    @Override
    public void run() {
        latch=new CountDownLatch(1);
        InetSocketAddress inetSocketAddress = new InetSocketAddress(host, port);
        client.connect(inetSocketAddress,this,this);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void completed(Void result, AsynchoTimeClientHandler attachment) {
        byte[] req="QUERY TIME ORDER".getBytes();
        ByteBuffer  writeBuffer=ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        client.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer buffer) {
               if(buffer.hasRemaining()){
                   //1.写数据
                    client.write(buffer,buffer,this);
               }else{
                   //2.读取
                   ByteBuffer   readBuffer=ByteBuffer.allocate(1024);
                   client.read(
                           readBuffer,
                           readBuffer,
                           new CompletionHandler<Integer, ByteBuffer>() {
                               @Override
                               public void completed(Integer result, ByteBuffer buffer) {
                                   buffer.flip();
                                   byte[] bytes=new byte[buffer.remaining()];
                                   buffer.get(bytes);
                                   String body;
                                   try {
                                       body=new String(bytes,"UTF-8");
                                       System.out.println("Now is :  "+body);
                                       latch.countDown();
                                   } catch (UnsupportedEncodingException e) {
                                       e.printStackTrace();
                                   }
                                   }
                                   @Override
                                   public void failed(Throwable exc, ByteBuffer buffer) {
                                       try {
                                           client.close();
                                           latch.countDown();
                                       } catch (IOException e) {
                                           e.printStackTrace();
                                       }

                                   }
                   });



               }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                try {
                    client.close();
                    latch.countDown();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

    }

    @Override
    public void failed(Throwable exc, AsynchoTimeClientHandler attachment) {
        exc.printStackTrace();
        try {
            client.close();
            latch.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
