package com.xuren.gameserver.net;

import com.google.protobuf.Message;
import com.xuren.common.cocurrent.FutureTaskScheduler;
import com.xuren.gameserver.net.proto.MsgBase;
import com.xuren.gameserver.proto.ProtoMsg3;

public class NetMsgDispatcher {

    public static void dispatcher(MsgBase msgBase) {
        FutureTaskScheduler.add(() ->
        {

        });
    }
}
