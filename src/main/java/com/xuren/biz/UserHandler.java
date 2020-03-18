package com.xuren.biz;

import com.xuren.gameserver.net.proto.MsgBase;
import com.xuren.gameserver.net.proto.MsgLoginCS;
import org.springframework.stereotype.Component;

@Component
public class UserHandler implements AbstractHandler{

    @Override
    public void handle(MsgBase msgBase) {
        if(msgBase instanceof MsgLoginCS) {
            MsgLoginCS msgLoginCS = (MsgLoginCS)msgBase;
            login(msgLoginCS.getId(), msgLoginCS.getPw());
        }
    }

    public boolean register(String account, String password) {
        System.out.println(account);
        return false;
    }

    public boolean login(String account, String password) {
        return true;
    }

    public void logout(String sessionId) {

    }

}
