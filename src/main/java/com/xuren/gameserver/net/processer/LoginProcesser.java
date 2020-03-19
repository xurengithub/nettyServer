package com.xuren.gameserver.net.processer;

import com.xuren.common.bean.User;
import com.xuren.gameserver.net.NetProtoConst;
import com.xuren.gameserver.net.ServerSession;
import com.xuren.gameserver.net.SessionMap;
import com.xuren.gameserver.net.proto.MsgBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("LoginProcesser")
public class LoginProcesser extends AbstractServerProcesser {

    @Override
    public int type() {
        return NetProtoConst.MSG_LOGIN_CS;
    }

    @Override
    public boolean action(ServerSession session,
                          MsgBase proto) {
//        // 取出token验证
//        ProtoMsg.LoginRequest info = proto.getLoginRequest();
//        long seqNo = proto.getSequence();
//
//        User user = User.fromMsg(info);
//
//        //检查用户
//        boolean isValidUser = checkUser(user);
//        if (!isValidUser) {
//            ProtoInstant.ResultCodeEnum resultcode =
//                    ProtoInstant.ResultCodeEnum.NO_TOKEN;
//            //构造登录失败的报文
//            ProtoMsg.Message response =
//                    loginResponceBuilder.loginResponce(resultcode, seqNo, "-1");
//            //发送登录失败的报文
//            session.writeAndFlush(response);
//            return false;
//        }
//
//        session.setUser(user);
//
//        session.bind();
//
//        //登录成功
//        ProtoInstant.ResultCodeEnum resultcode =
//                ProtoInstant.ResultCodeEnum.SUCCESS;
//        //构造登录成功的报文
//        ProtoMsg.Message response =
//                loginResponceBuilder.loginResponce(
//                        resultcode, seqNo, session.getSessionId());
//        //发送登录成功的报文
//        session.writeAndFlush(response);
        return true;
    }

    private boolean checkUser(User user) {

        if (SessionMap.inst().hasLogin(user)) {
            return false;
        }

        //校验用户,比较耗时的操作,需要100 ms以上的时间
        //方法1：调用远程用户restfull 校验服务
        //方法2：调用数据库接口校验

        return true;

    }

}
