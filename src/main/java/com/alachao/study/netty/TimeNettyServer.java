package com.alachao.study.netty;

import com.alachao.study.io.socket.noaio.TimeServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @Author yuliang-ds1
 * @Date 2019/3/18 14:48
 * @Description
 */
public class TimeNettyServer {

    //配置服务端的NIO线程组
    public void bind(int port)throws  Exception{

        EventLoopGroup bossGroup=new NioEventLoopGroup(1);
        EventLoopGroup workerGroup=new NioEventLoopGroup(2);
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).
                    channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());

            //绑定端口 同步等待成功
            ChannelFuture f = b.bind(port).sync();

            //等待服务器监听端口关闭
            f.channel().closeFuture().sync();

        }finally {
            //优雅退出   释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }

    }



    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
           // socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
           // socketChannel.pipeline().addLast(new StringDecoder());
            socketChannel.pipeline().addLast("frameDecoder",new LengthFieldBasedFrameDecoder(63255,0,2,0,2));
            socketChannel.pipeline().addLast("msgpack decoder",new MsgpackDecoder());
            socketChannel.pipeline().addLast("frameEncoder",new LengthFieldPrepender(2));
            socketChannel.pipeline().addLast("msgpack encoder",new MsgpackEncoder());
            socketChannel.pipeline().addLast(new TimeNettyServerHandler());
        }
    }

    public  static  void main(String args[]) throws Exception{
        int  port =8080;
        TimeNettyServer timeNettyServer = new TimeNettyServer();
        timeNettyServer.bind(port);
    }

}
