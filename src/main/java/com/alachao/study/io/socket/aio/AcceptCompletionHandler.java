package com.alachao.study.io.socket.aio;

import io.netty.buffer.ByteBufUtil;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @Author yuliang-ds1
 * @Date 2019/3/18 11:03
 * @Description
 */
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel,AsyncTimeServerHandler>{


    public  AcceptCompletionHandler(){
    }

    @Override
    public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
        attachment.asynchronousServerSocketChannel.accept(attachment,this );
        ByteBuffer  buffer=ByteBuffer.allocate(1024);
        ReadCompleteHandler readCompleteHandler = new ReadCompleteHandler(result);
        result.read(buffer, buffer,readCompleteHandler);
    }


    @Override
    public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
        exc.printStackTrace();
        attachment.latch.countDown();
    }


}

