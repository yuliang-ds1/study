package com.alachao.study.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledDirectByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * @Author yuliang-ds1
 * @Date 2019/3/18 16:32
 * @Description
 */
public class TimeNettyServerHandler  extends ChannelHandlerAdapter {

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //ByteBuf  byteBuf=(ByteBuf)msg;
        //byte[] req=new byte[byteBuf.readableBytes()];
        //byteBuf.readBytes(req);

        //String body=new String(req,"UTF-8").substring(0,req.length
        //        -System.getProperty("line.separator").length());
        Object  s=msg;
        if(s.equals("2")){

            System.out.println("Server-channelRead:"+s);


        }
        System.out.println("Server-channelRead:"+msg);

        //String body=(String)msg;
        //System.out.println("The Time Server Receive Order: "+body +"  ; the counter is : "+ ++counter);
        //String currentTime="QUERY TTIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
        //currentTime=currentTime+System.getProperty("line.separator");
        //ByteBuf  resp= Unpooled.copiedBuffer(currentTime.getBytes());

        ctx.writeAndFlush(msg);
        //ctx.writeAndFlush(resp);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
