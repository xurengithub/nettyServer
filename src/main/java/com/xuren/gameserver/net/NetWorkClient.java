package com.xuren.gameserver.net;

import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.xuren.common.utils.ArrayUtils;
import com.xuren.common.utils.ServNetUtils;
import com.xuren.common.utils2.JsonUtil;
import com.xuren.gameserver.net.proto.MsgBase;
import com.xuren.gameserver.net.proto.MsgLoginCS;
import com.xuren.gameserver.proto.ProtoMsg3;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Scanner;

public class NetWorkClient {
    private Bootstrap bootstrap;
    public void runClient() {
        bootstrap = new Bootstrap();
        EventLoopGroup worker = new NioEventLoopGroup(1);
        try {
            bootstrap.group(worker)
                    .channel(NioSocketChannel.class)
                    .remoteAddress("127.0.0.1", 8099)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                }
            });
            ChannelFuture channelFuture = bootstrap.connect();
            channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if(future.isSuccess()) {
                        System.out.println("客户端链接成功");
                    } else {
                        System.out.println("客户端链接失败");
                    }
                }
            });
            channelFuture.sync();
            Channel c = channelFuture.channel();
            Scanner s = new Scanner(System.in);

            for (int i = 0; i < 1; i++) {
                MsgLoginCS msgLoginCS = new MsgLoginCS();
                msgLoginCS.setId("xurenzuishuai");
                msgLoginCS.setPw("kuaibeicheng");

                byte[] bytes = JsonUtil.Object2JsonBytes(msgLoginCS);
                byte[] bytes1 = ServNetUtils.intToByteArray(msgLoginCS.getType());

                ByteBuf b = Unpooled.buffer();
                System.out.println(bytes.length);
                b.writeInt(bytes.length+bytes1.length);
                b.writeInt(msgLoginCS.getType());
                b.writeBytes(bytes);
                c.writeAndFlush(b);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
//        bootstrap
    }
    public static void main(String[] args) {
        new NetWorkClient().runClient();
    }
}
