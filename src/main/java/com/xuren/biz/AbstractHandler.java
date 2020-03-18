package com.xuren.biz;

import com.xuren.gameserver.net.proto.MsgBase;

public interface AbstractHandler {
    void handle(MsgBase msgBase);
}
