package com.alachao.study.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * @Author yuliang-ds1
 * @Date 2019/3/19 10:13
 * @Description
 */
public class EchoClient {

    private  final  String  host;
    private  final  int     port;
    private  final  int     sendNumber;


    public  EchoClient(String host,int port ,int sendNumber){
        this.host=host;
        this.port=port;
        this.sendNumber=sendNumber;
    }



    public  void  run()throws Exception{
        //configure  the  client
        //https://www.jianshu.com/p/f7c180b16a12
        EventLoopGroup  group=new NioEventLoopGroup();

        try{
        Bootstrap bootStrap=new Bootstrap();
        bootStrap.group(group)
                 .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,3000)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //处理半包消息
                        //这里设置通过增加包头表示报文长度来避免粘包
                        socketChannel.pipeline().addLast("frameDecoder",new LengthFieldBasedFrameDecoder(63255,0,2,0,2));
                        //增加解码器
                        socketChannel.pipeline().addLast("msgpack decoder",new MsgpackDecoder());
                        //这里设置读取报文的包头长度来避免粘包
                        socketChannel.pipeline().addLast("frameEncoder",new LengthFieldPrepender(2));
                        //增加编码器
                        socketChannel.pipeline().addLast("msgpack encoder",new MsgpackEncoder());
                        socketChannel.pipeline().addLast(new EchoClientHandler(sendNumber));


                    }
                });


        //发起异步连接操作
        ChannelFuture future=bootStrap.connect(host,port).sync();
        //等待客户端链路关闭
        future.channel().closeFuture().sync();

    }finally {
        //优雅退出 释放NIO线程组
            group.shutdownGracefully();

    }


    }



    public static void main(String args[]) throws Exception {
        EchoClient echoClient = new EchoClient("127.0.0.1", 8080, 20);
        echoClient.run();
    }

}
