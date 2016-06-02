package com.ballchen.education.user.dao;

import com.ballchen.education.user.entity.UserBasic;

public interface IUserBasicDAO {
    int deleteByPrimaryKey(String id);

    int insert(UserBasic record);

    int insertSelective(UserBasic record);

    UserBasic selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserBasic record);

    int updateByPrimaryKeyWithBLOBs(UserBasic record);

    int updateByPrimaryKey(UserBasic record);
}