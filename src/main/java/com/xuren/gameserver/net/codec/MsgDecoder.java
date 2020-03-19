package com.xuren.gameserver.net.codec;

import com.xuren.common.utils2.JsonUtil;
import com.xuren.gameserver.net.NetworkMsgMap;
import com.xuren.gameserver.net.exception.InvalidFrameException;
import com.xuren.gameserver.net.proto.MsgBase;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
// 标记一下当前的readIndex的位置
        in.markReaderIndex();
        // 判断包头长度
        if (in.readableBytes() < 4) {// 不够包头
            return;
        }

        // 读取传送过来的消息的长度。
        int length = in.readInt();

        // 长度如果小于0
        if (length < 0) {// 非法数据，关闭连接
            ctx.close();
        }

        if (length > in.readableBytes()) {// 读到的消息体长度如果小于传送过来的消息长度
            // 重置读取位置
            in.resetReaderIndex();
            return;
        }
        // 读取类型
        int type = in.readInt();

        byte[] array;
        if (in.hasArray()) {
            //堆缓冲
            ByteBuf slice = in.slice();
            array = slice.array();
        } else {
            //直接缓冲
            array = new byte[length-4];
            in.readBytes(array, 0, length-4);
        }

//        if(in.refCnt()>0)
//        {
////            log.debug("释放临时缓冲");
//            in.release();
//        }

        // 字节转成对象
        MsgBase msgBase = (MsgBase) JsonUtil.JsonBytes2Object(array, NetworkMsgMap.getClassByProtoType(type));


        if (msgBase != null) {
            // 获取业务消息
            out.add(msgBase);
        }
    }
}
