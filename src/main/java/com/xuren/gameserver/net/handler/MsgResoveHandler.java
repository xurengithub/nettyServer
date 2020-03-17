package com.xuren.gameserver.net.handler;

import com.xuren.common.utils2.JsonUtil;
import com.xuren.gameserver.net.NetMsgDispatcher;
import com.xuren.gameserver.net.NetworkMsgMap;
import com.xuren.gameserver.net.proto.MsgBase;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class MsgResoveHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        try {
            // 协议类型
            int protoType = byteBuf.readInt();
            // 协议对象
            byte[] bytes = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(bytes);

            MsgBase msgBase = (MsgBase) JsonUtil.JsonBytes2Object(bytes, NetworkMsgMap.getClassByProtoType(protoType));
            // 添加到消息队列
            NetMsgDispatcher.dispatcher(msgBase);
        } finally {
            ReferenceCountUtil.release(byteBuf);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause);
    }
}
