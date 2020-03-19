package com.xuren.gameserver.net.codec;

import com.xuren.common.utils.ServNetUtils;
import com.xuren.common.utils2.JsonUtil;
import com.xuren.gameserver.net.proto.MsgBase;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MsgEncoder extends MessageToByteEncoder<MsgBase> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MsgBase msg, ByteBuf out) throws Exception {
        byte[] bytes = JsonUtil.Object2JsonBytes(msg);
        byte[] bytes1 = ServNetUtils.intToByteArray(msg.getType());

        System.out.println(bytes.length);
        out.writeInt(bytes.length+bytes1.length);
        out.writeInt(msg.getType());
        out.writeBytes(bytes);
    }
}
