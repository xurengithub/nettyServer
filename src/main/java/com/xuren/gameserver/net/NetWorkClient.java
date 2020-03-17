package com.xuren.gameserver.net;

import com.google.protobuf.InvalidProtocolBufferException;
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

            for (int i = 0; i < 100; i++) {
                ProtoMsg3.Message.Builder b1 = ProtoMsg3.Message.newBuilder();
                ProtoMsg3.MsgLoginCS.Builder b2 = ProtoMsg3.MsgLoginCS.newBuilder();
                b2.setPassword("kuaibeicheng");
                b2.setUserName("xuren");
                ProtoMsg3.MsgLoginCS msgLoginCS = b2.build();
                b1.setMsgLoginCS(msgLoginCS);
                ProtoMsg3.Message m = b1.setSessionId("1213").setType(ProtoMsg3.HeadType.MSG_LOGIN_CS).build();


                byte[] bytes = m.toByteArray();
                ProtoMsg3.Message outmsg = ProtoMsg3.Message.parseFrom(bytes);
                System.out.println(new String(bytes));
                ByteBuf b = Unpooled.buffer();
                b.writeInt(bytes.length);
                b.writeBytes(bytes);
                c.writeAndFlush(b);
            }

        } catch (InterruptedException | InvalidProtocolBufferException e) {
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
