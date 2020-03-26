package com.xuren.gameserver.net.proto;

import com.xuren.gameserver.net.NetProtoConst;

public class MsgLoginSC  extends MsgSC {
    public MsgLoginSC() {
        type = NetProtoConst.MSG_LOGIN_SC;
    }
}
