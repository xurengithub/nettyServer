package com.xuren.gameserver.net.handler;

import com.xuren.gameserver.net.NetMsgDispatcher;
import com.xuren.gameserver.net.ServerSession;
import com.xuren.gameserver.net.proto.MsgBase;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@ChannelHandler.Sharable
public class MsgDispatcherHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //判断消息实例
        if (null == msg || !(msg instanceof MsgBase)) {
            super.channelRead(ctx, msg);
            return;
        }

        MsgBase msgBase = (MsgBase) msg;
        NetMsgDispatcher.dispatcher(ctx.channel().attr(ServerSession.SESSION_KEY).get(), msgBase);
    }
}
