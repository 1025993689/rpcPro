
package com.lhc.register.service.impl;

import com.lhc.register.handler.InboundHandler;
import com.lhc.register.service.RegisterCenterFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 默认的注册中心工厂实现类，基于 Netty 实现一个简单的 TCP 服务器。
 */
public class DefaultRegisterCenterFactoryImpl implements RegisterCenterFactory {
    @Override
    public void start(Integer port) {
        // 创建两个 NioEventLoopGroup，分别用于处理 Boss 任务（接受连接）和 Worker 任务（处理 I/O 事件）
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(10);

        try {
            // 创建 ServerBootstrap 对象，用于配置和启动 Netty 服务器
            ServerBootstrap bootstrap = new ServerBootstrap();

            // 设置事件循环组，使用 NioServerSocketChannel 作为服务器的通道类型
            ChannelFuture channelFuture = bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)

                    // 设置子通道（即每个新接受的连接）的处理器链，这里添加了 StringDecoder 和 StringEncoder 用于处理字符串消息
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new InboundHandler());
                            socketChannel.pipeline().addLast(new StringEncoder());
                        }
                    })

                    // 设置服务器选项，SO_BACKLOG 控制了全连接队列的最大长度，防止过快的连接请求导致拒绝服务
                    .option(ChannelOption.SO_BACKLOG, 128)

                    // 绑定到指定端口并同步等待服务器启动完成
                    .bind(port).sync();



            // 输出服务器启动信息
            System.out.println("Server started on port: " + port);
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            // 如果在启动过程中发生异常，抛出一个运行时异常以便上层捕获
            throw new RuntimeException("Failed to start server on port " + port, e);
        } finally {
            // 无论是否成功启动，都确保在方法结束时优雅地关闭事件循环组
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}