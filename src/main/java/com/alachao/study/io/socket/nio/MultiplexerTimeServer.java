package com.alachao.study.io.socket.nio;

import org.apache.ibatis.annotations.SelectKey;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author yuliang-ds1
 * @Date 2019/3/15 15:04
 * @Description
 */
public class MultiplexerTimeServer implements  Runnable {


    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

    private volatile boolean stop;

    /**
     * 初始化多路复用器，绑定监听端口
     * 1.创建多路复用器Selector
     * 2.创建监听TCP连接请求的通道 ServerSocketChannel
     * 3.设置通道异步非阻塞模式
     * 4.多路复用器监听通道上连接请求
     * @param port
     */
    public MultiplexerTimeServer(int port){

        try {
            selector=Selector.open();
            serverSocketChannel=ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port),1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The Time Server is start in port: "+port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public  void stop(){
        this.stop=true;
    }

    /**
     *
     */
    @Override
    public void run() {

        while(!stop){
            try {
                //1.休眠等待1s后返回
                //select() 选择一些I/O操作已经准备好的管道。每个管道对应着一个key。这个方法 是一个阻塞的选择操作。当至少有一个通道被选择时才返回。当这个方法被执行时，当前线程是允许被中断的。
                //select(long timeout)
                //如果 timeout为正，则select(long timeout)在等待有通道被选择时至多会阻塞timeout毫秒
                //如果timeout为零，则永远阻塞直到有至少一个通道准备就绪。
                //timeout不能为负数
                //无论是否有读写时间发生，selector 每隔1s都会被唤醒一次。无参时候，当有通道事件被选择之后才会返回
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key=null;
                while(iterator.hasNext()){
                    key=iterator.next();
                    iterator.remove();
                    try {
                        handleInput(key);
                    }catch (IOException e){
                        if(key!=null){
                            key.cancel();
                            if(key.channel()!=null){
                                key.channel().close();
                            }
                        }
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if(selector!=null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }



    private  void  handleInput(SelectionKey key) throws IOException{

        if(key.isValid()){
            //处理新接入的请求消息
            if(key.isAcceptable()){
                //如果是网络事件，那么通过通道accept方法接收客户端的连接请求并创建SocketChannel实例；
                //相当于完成了TCP三次握手，TCP物理连接正式建立
                //设置通道为异步非阻塞模式，同时也可以对TCP参数进行设置 例如TCP接收发送缓存区大小 ，这里没有
                //Accept  the  new  connection
                ServerSocketChannel ssc=(ServerSocketChannel)key.channel();
                SocketChannel sc=ssc.accept();
                sc.configureBlocking(false);
                //Add the new Connection to the selector
                sc.register(selector,SelectionKey.OP_READ);
            }

            if(key.isReadable()){
                //Read  the  Data
                //开辟一个1024字节的缓冲区
                //读写请求码流
                //使用返回值进行判断，看到读取到的字节数，返回值有三种 读到  没有读到 链路关闭需要关闭SocketChannel 释放资源

                SocketChannel  sc=(SocketChannel)key.channel();
                ByteBuffer readBuffer=ByteBuffer.allocate(1024);
                int readBytes=sc.read(readBuffer);
                if(readBytes>0){
                    /* 用于后续对缓冲区的读取操作你还敢
                    flip limit = position;
                    position = 0;
                    mark = -1;*/
                    readBuffer.flip();
                    byte[] bytes=new byte[readBuffer.remaining()];
                    //将缓存区可读的字节复制到字节数据中
                    readBuffer.get(bytes);
                    String body=new String(bytes,"UTF-8");
                    System.out.println("The Time Server Receive order: "+body);

                    String currentTime="QUERY TTIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
                    doWrite(sc,currentTime);
                }else if(readBytes<0){
                    //对端链路关闭
                    key.cancel();
                    sc.close();
                }else;//读到O字节 忽略不计

            }

        }

    }

    /**
     * 1.将应答消息异步发送给客户端
     *
     * @param socketChannel
     * @param response
     * @throws IOException
     */
    public void doWrite(SocketChannel socketChannel,String response)throws IOException{

        System.out.println("DoWrite-----:"+response);
        if(response!=null&&response.trim().length()>0){
            byte[] bytes=response.getBytes();
            ByteBuffer writeBuffer=ByteBuffer.allocate(bytes.length);
            //1.将应答字节数组复制到缓存区中
            writeBuffer.put(bytes);
            writeBuffer.flip();
            //异步非阻塞，并不能保证一次把需要发送的字节数组发送完，会出现写半包的问题，需要注册写操作
            //不断轮询Selector将没有发送完的ByteBuffer发送完毕，然后可以通过ByteBuffer的hasRemain()
            //方法判断小时是否发送完成
            socketChannel.write(writeBuffer);
        }

    }
}
