package com.xuren.service;

import com.xuren.dao.entity.UserInfoEntity;
import com.xuren.dao.mapper.UserMapper;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Sid sid;

    public void saveUserInfo(String account, String password) {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setId(sid.nextShort());
        userInfoEntity.setNickName("");
        userInfoEntity.setAccount(account);
        userInfoEntity.setPassword(password);

        userMapper.saveOne(userInfoEntity);
    }

    public void Update(UserInfoEntity userInfoEntity) {
        userMapper.updateOne(userInfoEntity);
    }
}
