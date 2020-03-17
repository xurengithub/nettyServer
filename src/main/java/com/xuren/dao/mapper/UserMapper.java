package com.xuren.dao.mapper;

import com.xuren.dao.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void saveOne(UserInfoEntity user);
    void queryAllUsers();
}
