package com.lhc.register.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;

/**
 * 服务端的连接请求
 */
public class InboundHandler implements ChannelInboundHandler {
    @Override
    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println(channelHandlerContext.channel().remoteAddress().toString());

    }

    @Override
    public void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println(1);
    }

    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println(1);

    }

    @Override
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println(1);

    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println(1);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println(1);

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {

    }
}
