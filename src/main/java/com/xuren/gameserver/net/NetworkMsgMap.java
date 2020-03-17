package com.xuren.gameserver.net;

import com.xuren.gameserver.proto.ProtoMsg3;

import java.util.HashMap;
import java.util.Map;

public class NetworkMsgMap {
    private static Map<Integer, Class> map = new HashMap<>();

    /**
     * 配置协议映射
     */
    static {
        map.put(NetProtoConst.MSG_LOGIN_CS, ProtoMsg3.MsgLoginCS.class);
    }

    public static Class getClassByProtoType(int protoType) {
        return map.get(protoType);
    }
}
