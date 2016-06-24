package com.ballchen.education.security.dao;

import com.ballchen.education.security.entity.Role;

import java.util.List;

public interface IRoleDAO {
    int deleteByPrimaryKey(String id);
    int deleteAll();
    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    Role selectByRoleCode(String roleCode);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectRoleWithAuthorization();

    List<Role> getPageRoles();
}