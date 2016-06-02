package com.ballchen.education.security.dao;

import com.ballchen.education.security.entity.Authorization;
import com.ballchen.education.security.entity.Role;

import java.util.List;

public interface IAuthorizationDAO {
    int deleteByPrimaryKey(String id);

    int deleteAll();

    int insert(Authorization record);

    int insertSelective(Authorization record);

    Authorization selectByPrimaryKey(String id);

    Authorization selectAuthorizationWithRolesByAuthorizationCode(String authorizationCode);

    int updateByPrimaryKeySelective(Authorization record);

    int updateByPrimaryKey(Authorization record);

    List<Authorization> selectAuthorizationWithRoles();

    Authorization selectAuthorizationWithRoles(String authorizationCode);

    List<Authorization> selectAuthorizationWithRolesBySelective(Authorization authorization);
}