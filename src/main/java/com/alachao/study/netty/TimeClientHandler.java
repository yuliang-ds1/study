package com.alachao.study.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

/**
 * @Author yuliang-ds1
 * @Date 2019/3/18 17:16
 * @Description
 */
public class TimeClientHandler extends ChannelHandlerAdapter {


    public static final Logger logger=Logger.getLogger(TimeClientHandler.class.getName());

    //private final ByteBuf firstMessage;

    private int counter;
    private byte[] req;

    public  TimeClientHandler(){

        req =("QUERY TTIME ORDER"+System.getProperty("line.separator")).getBytes();
        //firstMessage= Unpooled.buffer(req.length);
        //firstMessage.writeBytes(req);

    }

    /**
     * 客户端TCP链路建立成功之后，Netty线程会调用ChannelActive方法  发送查询执行给服务端，调用
     * writeAndFlush方法将请求发送给服务端
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ByteBuf message=null;
        for (int i=0;i<100;i++){
            message= Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }

    }


    /**
     * 服务端应答消息后，channelRead方法被调用
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //ByteBuf  byteBuf=(ByteBuf)msg;
        //byte[]  req =new byte[byteBuf.readableBytes()];
        //byteBuf.readBytes(req);
        //String body=new String(req,"UTF-8");
        String body=(String)msg;
        System.out.println("Now is :"+body+"; the counter is :"+ ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       //释放资源
        cause.printStackTrace();
        ctx.close();
    }


}
