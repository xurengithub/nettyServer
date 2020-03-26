package com.xuren.gameserver.net.proto;

import com.xuren.gameserver.net.NetProtoConst;
import sun.nio.ch.Net;

public class MsgSC extends MsgBase {
    private int code;
    private String info;
    private String data;
    public MsgSC() {
        this.type = NetProtoConst.MSG_SC;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
