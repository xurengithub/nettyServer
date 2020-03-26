package com.xuren.gameserver.net.proto;

import com.xuren.gameserver.net.NetProtoConst;

public class MsgLogoutSC extends MsgSC{
    public MsgLogoutSC() {
        this.type = NetProtoConst.MSG_LOGOUT_SC;
    }
}
