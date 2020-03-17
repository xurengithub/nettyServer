package com.xuren.dao.mapper;

import com.xuren.dao.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int saveOne(UserInfoEntity user);
    List<UserInfoEntity> queryAllUsers();
}
