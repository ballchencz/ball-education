package com.ballchen.education.user.service;

import com.ballchen.education.user.entity.UserBasicRole;

/**
 * Created by ballchen on 2016/6/23.
 */
public interface IUserBasicRoleService {
    int deleteByPrimaryKey(String id);

    int insert(UserBasicRole record);

    int insertSelective(UserBasicRole record);

    UserBasicRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserBasicRole record);

    int updateByPrimaryKey(UserBasicRole record);
}
