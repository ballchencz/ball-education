package com.ballchen.education.security.dao;

import com.ballchen.education.security.entity.RoleAuthorization;

import java.util.List;
import java.util.Map;

public interface IRoleAuthorizationDAO {
    int deleteByPrimaryKey(String id);
    int deleteAll();

    int insert(RoleAuthorization record);

    int insertSelective(RoleAuthorization record);

    RoleAuthorization selectByPrimaryKey(String id);

    RoleAuthorization selectByRoleIdAndAuthorizationId(Map<String,String> queryMap);

    int updateByPrimaryKeySelective(RoleAuthorization record);

    int updateByPrimaryKey(RoleAuthorization record);

}