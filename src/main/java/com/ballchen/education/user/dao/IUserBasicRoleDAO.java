package com.ballchen.education.user.dao;

import com.ballchen.education.user.entity.UserBasicRole;

import java.util.Map;

public interface IUserBasicRoleDAO {
    int deleteByPrimaryKey(String id);

    int insert(UserBasicRole record);

    int insertSelective(UserBasicRole record);

    UserBasicRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserBasicRole record);

    int updateByPrimaryKey(UserBasicRole record);
    int deleteByUserBasicId(String userBasicId);
    int deleteByRoleId(String roleId);

    int deleteByUserBasicIds(Map<String,Object> paramMap);
}