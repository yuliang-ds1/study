package com.alachao.study.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.sql.Time;

/**
 * @Author yuliang-ds1
 * @Date 2019/3/18 16:53
 * @Description
 */
public class TimeNettyClient {

    public  void  connect (String host,int port)throws Exception{

        //配置客户端NIO的线程组

        EventLoopGroup eventLoopGroup=new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(eventLoopGroup)
            .channel(NioSocketChannel.class)
            .option(ChannelOption.SO_BACKLOG.TCP_NODELAY,true)
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                    socketChannel.pipeline().addLast(new StringDecoder());
                    socketChannel.pipeline().addLast(new TimeClientHandler());
                }
            }) ;


            //发起异步连接操作
            ChannelFuture future=b.connect(host,port).sync();


            //等待客户端链路关闭
            future.channel().closeFuture().sync();


        }finally {

            //优雅退出 释放NIO线程组
            eventLoopGroup.shutdownGracefully();

        }

    }



    public static  void main(String args[])throws Exception{
        int port=8080;
        new TimeNettyClient().connect("127.0.0.1",port);

    }
}
