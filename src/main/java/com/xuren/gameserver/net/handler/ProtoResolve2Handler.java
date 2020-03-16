package com.xuren.gameserver.net.handler;

import com.xuren.gameserver.proto.ProtoMsg3;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ProtoResolve2Handler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //判断消息实例
        if (null == msg || !(msg instanceof ProtoMsg3.ProtoMsg.Message)) {
            super.channelRead(ctx, msg);
            return;
        }

        //判断消息类型
        ProtoMsg3.ProtoMsg.Message pkg = (ProtoMsg3.ProtoMsg.Message) msg;
        ProtoMsg3.ProtoMsg.HeadType headType = ((ProtoMsg3.ProtoMsg.Message) msg).getType();
        if(headType.equals(ProtoMsg3.ProtoMsg.HeadType.LOGIN_REQUEST)) {
            //登录

        }
        // 根据类型分发消息

    }
}
