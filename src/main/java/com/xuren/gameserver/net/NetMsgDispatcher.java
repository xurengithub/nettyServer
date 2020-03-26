package com.xuren.gameserver.net;

import com.google.protobuf.Message;
import com.xuren.common.cocurrent.CallbackTask;
import com.xuren.common.cocurrent.CallbackTaskScheduler;
import com.xuren.common.cocurrent.FutureTaskScheduler;
import com.xuren.common.utils.Tools;
import com.xuren.gameserver.net.proto.MsgBase;
import com.xuren.gameserver.proto.ProtoMsg3;

public class NetMsgDispatcher {

    public static void dispatcher(int protoType, MsgBase msgBase) {
        NetworkMsgMap.getHander(protoType).handle(msgBase);
    }

    public static void dispatcher(ServerSession session, MsgBase msg) {

        CallbackTaskScheduler.add(new CallbackTask<Boolean>() {
            @Override
            public Boolean execute() throws Exception {
                boolean r = NetworkMsgMap.getProcesser(msg.getType()).action(session, msg);
                return r;
            }

            //异步任务返回
            @Override
            public void onBack(Boolean r) {
                if (r) {

                } else {

                }

            }
            //异步任务异常

            @Override
            public void onException(Throwable t) {
//                log.info("登录失败:" + session.getUser());

            }
        });
    }
}
