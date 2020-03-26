package com.xuren.gameserver.net;

import com.xuren.biz.AbstractHandler;
import com.xuren.biz.UserHandler;
import com.xuren.common.utils2.SpringUtil;
import com.xuren.gameserver.net.processer.AbstractServerProcesser;
import com.xuren.gameserver.net.processer.LoginProcesser;
import com.xuren.gameserver.net.processer.LogoutProcesser;
import com.xuren.gameserver.net.proto.MsgLoginCS;
import com.xuren.gameserver.net.proto.MsgLogoutCS;
import com.xuren.gameserver.proto.ProtoMsg3;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class NetworkMsgMap {
    private static Map<Integer, Class<?>> map = new HashMap<>();
    private static Map<Integer, AbstractHandler> handlerMap = new HashMap<>();
    private static Map<Integer, AbstractServerProcesser> processerMap = new HashMap<>();
    /**
     * 配置协议映射
     */
    static {
        map.put(NetProtoConst.MSG_LOGIN_CS, MsgLoginCS.class);
        map.put(NetProtoConst.MSG_LOGOUT_CS, MsgLogoutCS.class);
    }

    public static void init(ApplicationContext context) {
//        UserHandler userHandler = (UserHandler) context.getBean("userHandler");
        UserHandler userHandler = SpringUtil.getBean(UserHandler.class);
        handlerMap.put(NetProtoConst.MSG_LOGIN_CS, userHandler);
    }

    public static void initProcesser(ApplicationContext context) {
        LoginProcesser loginProcesser = SpringUtil.getBean(LoginProcesser.class);
        LogoutProcesser logoutProcesser = SpringUtil.getBean(LogoutProcesser.class);
        processerMap.put(logoutProcesser.type(), logoutProcesser);
        processerMap.put(loginProcesser.type(), loginProcesser);
    }

    /**
     * 通过协议类型获取协议类class
     * @param protoType
     * @return
     */
    public static Class getClassByProtoType(int protoType) {
        return map.get(protoType);
    }

    /**
     * 通过协议类型获取协议处理模块
     * @param protoType
     * @return
     */
    public static AbstractHandler getHander(int protoType) {
        return handlerMap.get(protoType);
    }

    public static AbstractServerProcesser getProcesser(int protoType) {
        return processerMap.get(protoType);
    }
}
