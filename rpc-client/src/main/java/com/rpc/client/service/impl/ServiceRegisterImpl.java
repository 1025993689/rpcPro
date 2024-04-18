package com.rpc.client.service.impl;

import com.rpc.client.service.ServiceRegitser;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class ServiceRegisterImpl implements ServiceRegitser {

    @Override
    public void register(String serviceName, InetSocketAddress address) {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>(){

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 添加编解码器，这里以字符串为例
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new StringEncoder());
                        }
                    });

            // 连接到服务器
            ChannelFuture channelFuture = bootstrap.connect(address.getHostString(), address.getPort()).sync();

            // 关闭连接前阻塞主线程
            channelFuture.channel().closeFuture().sync();


        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
