package com.xuren.gameserver.net;

public class NetProtoConst {

    public static final int MSG_HEART_BEAT = 1;
    public static final int MSG_SC = 2;

    /**
     * 用户相关协议10000-19999
     */
    public static final int MSG_LOGIN_CS = 10001;
    public static final int MSG_LOGIN_SC = 10002;

    public static final int MSG_LOGOUT_CS = 10003;
    public static final int MSG_LOGOUT_SC = 10004;

    /**
     * 游戏相关协议20000-29999
     */
    public static final int MSG_SYNC_OPERATION = 20001;
    public static final int MSG_NO_LOGIN = 20002;

}
