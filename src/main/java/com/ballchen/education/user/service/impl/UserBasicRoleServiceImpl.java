package com.ballchen.education.user.service.impl;

import com.ballchen.education.user.dao.IUserBasicRoleDAO;
import com.ballchen.education.user.entity.UserBasicRole;
import com.ballchen.education.user.service.IUserBasicRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/23.
 */
@Service
public class UserBasicRoleServiceImpl implements IUserBasicRoleService {

    @Autowired
    private IUserBasicRoleDAO userBasicRoleDAO;


    @Override
    public int deleteByPrimaryKey(String id) {
        return userBasicRoleDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserBasicRole record) {
        return userBasicRoleDAO.insert(record);
    }

    @Override
    public int insertSelective(UserBasicRole record) {
        return userBasicRoleDAO.insertSelective(record);
    }

    @Override
    public UserBasicRole selectByPrimaryKey(String id) {
        return userBasicRoleDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserBasicRole record) {
        return userBasicRoleDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserBasicRole record) {
        return userBasicRoleDAO.updateByPrimaryKey(record);
    }
}
