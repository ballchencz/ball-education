package com.ballchen.education.user.service.impl;

import com.ballchen.education.admin.entity.PageHelper;
import com.ballchen.education.admin.utils.AdminUtils;
import com.ballchen.education.user.dao.IUserBasicDAO;
import com.ballchen.education.user.entity.UserBasic;
import com.ballchen.education.user.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/19.
 */
@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserBasicDAO userBasicDAO;

    @Override
    public int deleteByPrimaryKey(String id) {
        return userBasicDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserBasic record) {
        return userBasicDAO.insert(record);
    }

    @Override
    public int insertSelective(UserBasic record) {
        return userBasicDAO.insertSelective(record);
    }

    @Override
    public UserBasic selectByPrimaryKey(String id) {
        return userBasicDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserBasic record) {
        return userBasicDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(UserBasic record) {
        return userBasicDAO.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(UserBasic record) {
        return userBasicDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<UserBasic> getUserBasicPagination(UserBasic userBasic, PageHelper pageHelper) {
        Map<String,Object> queryMap = AdminUtils.parsePOJOtoMap(userBasic);
        queryMap.putAll(AdminUtils.parsePOJOtoMap(pageHelper));
        List<UserBasic> userBasics = this.userBasicDAO.getUserBasicPagination(queryMap);
        return userBasics;
    }

    @Override
    public Long getUserBasicPaginationCount(UserBasic userBasic, PageHelper pageHelper) {
        Map<String,Object> queryMap = AdminUtils.parsePOJOtoMap(userBasic);
        queryMap.putAll(AdminUtils.parsePOJOtoMap(pageHelper));
        Long count = this.userBasicDAO.getUserBasicPaginationCount(queryMap);
        return count;
    }

}
