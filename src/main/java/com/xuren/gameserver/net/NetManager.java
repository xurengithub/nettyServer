package com.xuren.gameserver.net;

import com.xuren.gameserver.net.proto.MsgBase;

import java.util.List;

public class NetManager {

    public void sendToAll(MsgBase msg) {

    }

    public void sendToUser(MsgBase msg, ServerSession session) {
        session.writeAndFlush(msg);
    }

    public void sendSome(MsgBase msg, List<ServerSession> list) {
        for (ServerSession serverSession : list) {
            serverSession.writeAndFlush(msg);
        }
    }

}
