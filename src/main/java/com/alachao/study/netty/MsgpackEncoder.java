package com.alachao.study.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * @Author yuliang-ds1
 * @Date 2019/3/19 10:05
 * @Description
 */
public class MsgpackEncoder  extends MessageToByteEncoder<Object> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
            //负责将Object类型POJO对象编码为byte数组  然后写入到ByteBuf中；
        MessagePack  messagePack=new MessagePack();
        byte[] write = messagePack.write(o);
        byteBuf.writeBytes(write);
    }
}
