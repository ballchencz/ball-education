package com.ballchen.education.security.service;

import com.ballchen.education.security.entity.Role;

import java.util.List;

/**
 * Created by Administrator on 2016/5/26.
 */
public interface IRoleService {
    void insertRole(Role role);
    int updateRole(Role role);
    Role selectByRoleCode(String roleCode);
    List<Role> selectRoleWithAuthorization();
    List<Role> getPageRoles();
}
