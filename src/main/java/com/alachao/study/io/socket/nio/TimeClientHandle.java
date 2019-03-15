package com.alachao.study.io.socket.nio;

import org.apache.ibatis.annotations.SelectKey;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.sql.Time;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author yuliang-ds1
 * @Date 2019/3/15 17:19
 * @Description
 */
public class TimeClientHandle implements  Runnable{
    private  String host;
    private   int   port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop;

    /**
     * 1.初始化NIO的多路复用器和SocketChannel对象
     * 2.设置异步非阻塞模式
     * @param host
     * @param port
     */
    public  TimeClientHandle(String host,int port){
            this.host=host==null?"127.0.0.1":host;
            this.port=port;
        try {
            selector=Selector.open();
            socketChannel=SocketChannel.open();
            socketChannel.configureBlocking(false);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }



    @Override
    public void run() {
        //1.发起连接请求
        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while(!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys =selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key=null;
                while(iterator.hasNext()){
                    key=iterator.next();
                    iterator.remove();
                    try {
                        handleInput(key);
                    }catch (IOException e){
                        e.printStackTrace();;
                        key.cancel();
                        if(key.channel()!=null){
                            key.channel().close();
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        //多路复用器关闭后，所有注册在上面的Channel和Pipe资源都会被自动注册并关闭，所有不需要重复释放资源
        if(selector!=null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    public void handleInput(SelectionKey key)throws IOException{

        if(key.isValid()){
            //判断是否连接成功
            SocketChannel socketChannel= (SocketChannel) key.channel();
            if(key.isConnectable()){
                if(socketChannel.finishConnect()){
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    doWrite(socketChannel);
                }else{
                    System.exit(1);
                    //连接失败，进程退出
                }

            }


            if(key.isReadable()){
                ByteBuffer readBuffer=ByteBuffer.allocate(1024);
                int readBytes=socketChannel.read(readBuffer);
                if(readBytes>0){
                    readBuffer.flip();
                    byte[]  bytes=new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String  body=new String(bytes,"UTF-8");
                    System.out.println("Now is: "+body);
                    this.stop=true;
                }else if(readBytes<0){
                    //对端链路关闭
                    key.channel();
                    socketChannel.close();
                }else{
                    ;//读到0字节忽略
                }

            }






        }

    }


    /**
     * 如果直接连接成功，则注册到多路复用器上，发送请求消息，读应答
     * 1.连接成功，则不需要做重连操作，因此将其放到循环之前，如果连接成功，
     * 则将SocketChannel注册到Selector上，注册READ事件
     *
     * 2.没有连上，则说明服务端没有返回TCP握手应答消息，但这并不代表连接失败。
     * 需要将SocketChannel注册到多路复用器Selector上，注册CONNECT时间，
     * 当TCP 返回syn-ack消息后，Selector就能够轮询到通道连接就绪的桩体
     * @throws IOException
     */
    public void  doConnect()throws  IOException{
        if(socketChannel.connect(new InetSocketAddress(host,port))){
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        }else{
            socketChannel.register(selector,SelectionKey.OP_CONNECT);
        }
    }

    /**
     * 写信息到服务器端
     * @param sc
     * @throws IOException
     */
    public void doWrite(SocketChannel sc)throws  IOException{
        byte[] req="QUERY TTIME ORDER".getBytes();
        ByteBuffer writeBuffer=ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        sc.write(writeBuffer);
        if(!writeBuffer.hasRemaining()){
            System.out.println("Send Order 2 Server Succeed. ");
        }
    }
}
