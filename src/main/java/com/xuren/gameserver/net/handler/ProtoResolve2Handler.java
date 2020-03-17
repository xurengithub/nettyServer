package com.xuren.gameserver.net.handler;

import com.xuren.gameserver.net.NetMsgDispatcher;
import com.xuren.gameserver.proto.ProtoMsg3;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ProtoResolve2Handler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //判断消息实例
        if (null == msg || !(msg instanceof ProtoMsg3.Message)) {
            super.channelRead(ctx, msg);
            return;
        }

        //判断消息类型
        ProtoMsg3.Message pkg = (ProtoMsg3.Message) msg;
        ProtoMsg3.HeadType headType = ((ProtoMsg3.Message) msg).getType();
        // 分发消息
        NetMsgDispatcher.dispatcher(headType, pkg);
    }
}
