package com.xuren.gameserver.net.proto;

import com.xuren.gameserver.net.NetProtoConst;

public class MsgLoginCS extends MsgBase{
    //客户端发
    private String id;
    private String pw;

    public MsgLoginCS() {
        type = NetProtoConst.MSG_LOGIN_CS;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

}
