package com.xuren.gameserver.net.processer;

import com.xuren.common.bean.User;
import com.xuren.gameserver.net.NetProtoConst;
import com.xuren.gameserver.net.ServerSession;
import com.xuren.gameserver.net.SessionMap;
import com.xuren.gameserver.net.proto.MsgBase;
import com.xuren.gameserver.net.proto.MsgLoginCS;
import com.xuren.gameserver.net.proto.MsgLoginSC;
import com.xuren.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Slf4j
@Service("LoginProcesser")
public class LoginProcesser extends AbstractServerProcesser {
    @Autowired
    private UserService userService;

    @Override
    public int type() {
        return NetProtoConst.MSG_LOGIN_CS;
    }

    @Override
    public boolean action(ServerSession session,
                          MsgBase proto) {
        MsgLoginCS msgLoginCS = (MsgLoginCS) proto;
        String account = msgLoginCS.getId();
        String password = msgLoginCS.getPw();

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);

//        // 取出token验证
//
//        User user = User.fromMsg(info);
//
//        //检查用户
        boolean isValidUser = checkUser(user);
        if (!isValidUser) {
            MsgLoginSC msgLoginSC = new MsgLoginSC();
            msgLoginSC.setCode(-1);
            //发送登录失败的报文
            session.writeAndFlush(msgLoginSC);
            return false;
        }

        session.setUser(user);
        // 设置登录成功、和channel绑定、放入sessionMap中
        session.bind();

        //登录成功
        //构造登录成功的报文
        MsgLoginSC msgLoginSC = new MsgLoginSC();
        msgLoginSC.setCode(1);
        //发送登录成功的报文
        session.writeAndFlush(msgLoginSC);
        return true;
    }

    private boolean checkUser(User user) {

        if (SessionMap.inst().hasLogin(user)) {
            return false;
        }

        //校验用户,比较耗时的操作,需要100 ms以上的时间
        //方法1：调用远程用户restfull 校验服务
        //方法2：调用数据库接口校验
        boolean is = userService.verifyAccountAndPassword(user);
        return userService.verifyAccountAndPassword(user);

//        return true;

    }

}
