package com.xuren.gameserver.net;

import com.xuren.gameserver.net.handler.ProtoResolveHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class NetWorkServer {
    private ServerBootstrap serverBootstrap = new ServerBootstrap();
    /**
     * 端口号
     */
    private int serverPort = 8099;

    public void runServer() {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup workers = new NioEventLoopGroup();
        try {
            serverBootstrap.group(boss, workers);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.localAddress(serverPort);
            serverBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            serverBootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024,0,4,0,4))
                            .addLast(new ProtoResolveHandler());
                }
            });
            ChannelFuture cf = serverBootstrap.bind().sync();
            System.out.println("服务器启动");
            ChannelFuture closeFuture = cf.channel().closeFuture();
            closeFuture.sync();
            System.out.println("服务器关闭");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            workers.shutdownGracefully();
        }

    }
    public static void main(String[] args) {
        NetWorkServer netWorkServer = new NetWorkServer();
        netWorkServer.runServer();
    }
}
