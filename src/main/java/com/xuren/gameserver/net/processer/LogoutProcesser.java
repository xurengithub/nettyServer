package com.xuren.gameserver.net.processer;

import com.xuren.gameserver.net.NetProtoConst;
import com.xuren.gameserver.net.ServerSession;
import com.xuren.gameserver.net.proto.MsgBase;
import com.xuren.gameserver.net.proto.MsgLogoutCS;

public class LogoutProcesser extends AbstractServerProcesser {
    @Override
    public int type() {
        return NetProtoConst.MSG_LOGOUT_CS;
    }

    @Override
    public boolean action(ServerSession serverSession, MsgBase msg) {
        MsgLogoutCS msgLogoutCS = (MsgLogoutCS) msg;
        if(serverSession.getSessionId().equals(msgLogoutCS.getSessionId())) {
            serverSession.unbind();
            return true;
        }
        return false;
    }
}
