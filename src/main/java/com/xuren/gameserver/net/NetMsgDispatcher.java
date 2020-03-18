package com.xuren.gameserver.net;

import com.google.protobuf.Message;
import com.xuren.common.cocurrent.FutureTaskScheduler;
import com.xuren.common.utils.Tools;
import com.xuren.gameserver.net.proto.MsgBase;
import com.xuren.gameserver.proto.ProtoMsg3;

public class NetMsgDispatcher {

    public static void dispatcher(int protoType, MsgBase msgBase) {
        NetworkMsgMap.getHander(protoType).handle(msgBase);
    }
}
