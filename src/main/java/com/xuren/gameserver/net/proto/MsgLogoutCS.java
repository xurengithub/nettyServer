package com.xuren.gameserver.net.proto;

import com.xuren.gameserver.net.NetProtoConst;

public class MsgLogoutCS extends MsgBase {
    private String sessionId;

    public MsgLogoutCS() {
        this.type = NetProtoConst.MSG_LOGOUT_CS;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
