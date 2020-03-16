package com.xuren.gameserver.net.handler;

import com.xuren.gameserver.events.LoginEvent;
import com.xuren.gameserver.events.SimpleApplicationEventMulticaster;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class ProtoResolveHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        try {
            int protoTag = byteBuf.readInt();
            byte[] bytes = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(bytes);
            if(protoTag == 10001) {

                UserModel.LoginMsg loginMsg = UserModel.LoginMsg.parseFrom(bytes);
                new SimpleApplicationEventMulticaster().multicastEvent(new LoginEvent(protoTag,0,loginMsg.getUserName(),loginMsg.getPassword()));
            }
        } finally {
            ReferenceCountUtil.release(byteBuf);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause);
    }
}
