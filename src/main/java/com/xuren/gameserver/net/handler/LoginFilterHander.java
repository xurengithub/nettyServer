package com.xuren.gameserver.net.handler;

import com.xuren.gameserver.net.ServerSession;
import com.xuren.gameserver.net.proto.MsgBase;
import com.xuren.gameserver.net.proto.MsgNoLogin;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LoginFilterHander extends ChannelInboundHandlerAdapter {

    private Logger log = LogManager.getLogger(LoginFilterHander.class);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //判断消息实例
        if (null == msg || !(msg instanceof MsgBase)) {
            super.channelRead(ctx, msg);
            return;
        }

        MsgBase msgBase = (MsgBase) msg;
        ServerSession serverSession = ctx.channel().attr(ServerSession.SESSION_KEY).get();
        String sessionId = msgBase.getSessionId();
        if(serverSession != null && sessionId != null && !sessionId.equals("") && sessionId.equals(serverSession.getSessionId())) {
            super.channelRead(ctx, msg);
            return;
        }
        log.debug("用户未登录");
        // 封装未登录消息
        ctx.channel().writeAndFlush(new MsgNoLogin());
    }
}
