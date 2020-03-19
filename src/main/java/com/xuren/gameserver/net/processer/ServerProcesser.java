package com.xuren.gameserver.net.processer;


import com.xuren.gameserver.net.ServerSession;
import com.xuren.gameserver.net.proto.MsgBase;

/**
 * 操作类
 */
public interface ServerProcesser {

    int type();

    boolean action(ServerSession ch, MsgBase proto);

}
