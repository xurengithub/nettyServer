package com.xuren.gameserver.net.proto;

import com.xuren.gameserver.net.NetProtoConst;

public class MsgNoLogin extends MsgBase {
    public MsgNoLogin() {
        this.type = NetProtoConst.MSG_NO_LOGIN;
    }
}
