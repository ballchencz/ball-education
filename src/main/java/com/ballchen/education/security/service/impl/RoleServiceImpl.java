package com.ballchen.education.security.service.impl;

import com.ballchen.education.security.dao.IRoleDAO;
import com.ballchen.education.security.entity.Role;
import com.ballchen.education.security.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/5/26.
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDAO roleDAO;
    @Override
    @Transactional
    public void insertRole(Role role) {
        this.roleDAO.insert(role);
    }

    @Override
    public int updateRole(Role role) {
        return this.roleDAO.updateByPrimaryKeySelective(role);
    }

    @Override
    public Role selectByRoleCode(String roleCode) {
        return this.roleDAO.selectByRoleCode(roleCode);
    }

    @Override
    public List<Role> selectRoleWithAuthorization() {
        return roleDAO.selectRoleWithAuthorization();
    }

    @Override
    public List<Role> getPageRoles() {
        return roleDAO.getPageRoles();
    }


}
