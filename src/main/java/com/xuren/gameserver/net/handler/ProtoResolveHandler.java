package com.xuren.gameserver.net.handler;

import com.xuren.gameserver.proto.ProtoMsg3;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;


public class ProtoResolveHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        try {
            byte[] bytes = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(bytes);
            ProtoMsg3.ProtoMsg.Message outmsg =
                    ProtoMsg3.ProtoMsg.Message.parseFrom(bytes);
            super.channelRead(ctx, outmsg);
        } finally {
            ReferenceCountUtil.release(byteBuf);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause);
    }
}
