package com.xuren.gameserver.net.handler;

import com.google.protobuf.GeneratedMessageV3;
import com.xuren.common.utils2.JsonUtil;
import com.xuren.gameserver.net.NetMsgDispatcher;
import com.xuren.gameserver.net.NetworkMsgMap;
import com.xuren.gameserver.net.proto.MsgBase;
import com.xuren.gameserver.net.proto.MsgLoginCS;
import com.xuren.gameserver.proto.ProtoMsg3;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class MsgResoveHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        try {
            int len = byteBuf.readInt();
            int type = byteBuf.readInt();
            System.out.println(type);

            // 协议对象
            byte[] bytes = new byte[len-4];
            byteBuf.readBytes(bytes);
            MsgBase msgBase = (MsgBase) JsonUtil.JsonBytes2Object(bytes, NetworkMsgMap.getClassByProtoType(type));
            if(msgBase instanceof MsgLoginCS) {
                System.out.println(((MsgLoginCS) msgBase).getId());
            }
//            NetMsgDispatcher.dispatcher(type, msgBase);
            // 添加到消息队列
        } finally {
            ReferenceCountUtil.release(byteBuf);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause);
    }
}
