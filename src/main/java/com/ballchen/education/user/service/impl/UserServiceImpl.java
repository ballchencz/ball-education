package com.ballchen.education.user.service.impl;

import com.ballchen.education.admin.entity.PageHelper;
import com.ballchen.education.admin.utils.AdminUtils;
import com.ballchen.education.security.dao.IRoleAuthorizationDAO;
import com.ballchen.education.security.entity.Role;
import com.ballchen.education.security.entity.RoleAuthorization;
import com.ballchen.education.security.service.IRoleService;
import com.ballchen.education.user.dao.IUserBasicDAO;
import com.ballchen.education.user.entity.UserBasic;
import com.ballchen.education.user.entity.UserBasicRole;
import com.ballchen.education.user.service.IUserBasicRoleService;
import com.ballchen.education.user.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Administrator on 2016/5/19.
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserBasicDAO userBasicDAO;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserBasicRoleService userBasicRoleService;

    @Override
    public int deleteByPrimaryKey(String id) {
        return userBasicDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserBasic record) {
        return userBasicDAO.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(UserBasic record) {
        //添加用户
        int i = userBasicDAO.insertSelective(record);
        if(record.getRoles()!=null){
            List<Role> roles = record.getRoles();
            for (Role role : roles){
                role = roleService.selectByRoleCode(role.getRoleCode());
                if(role!=null){
                    UserBasicRole userBasicRole = new UserBasicRole();
                    userBasicRole.setId(UUID.randomUUID().toString());
                    userBasicRole.setUserBasicId(record.getId());
                    userBasicRole.setRoleId(role.getId());
                    this.userBasicRoleService.insertSelective(userBasicRole);
                }
            }
        }
        return i;

    }

    @Override
    public UserBasic selectByPrimaryKey(String id) {
        return userBasicDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserBasic record) {
        int i =userBasicDAO.updateByPrimaryKeySelective(record);
        userBasicRoleService.deleteByUserBasicId(record.getId());
        for(Role role:record.getRoles()){
            if(role.getRoleCode()!=null){
                role = this.roleService.selectByRoleCode(role.getRoleCode());
                UserBasicRole userBasicRole = new UserBasicRole();
                userBasicRole.setId(UUID.randomUUID().toString());
                userBasicRole.setRoleId(role.getId());
                userBasicRole.setUserBasicId(record.getId());
                this.userBasicRoleService.insertSelective(userBasicRole);
            }
        }
        return  i;
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

    @Override
    public UserBasic selectUserBasicWithRolesByPrimaryKey(String id) {
        return this.userBasicDAO.selectUserBasicWithRolesByPrimaryKey(id);
    }

    @Override
    public int deleteByIds(String [] ids) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("ids",ids);
        this.userBasicRoleService.deleteByUserBasicIds(ids);
        return this.userBasicDAO.deleteByIds(paramMap);
    }

    @Override
    public int accessOrDeniedUser(String [] ids,UserBasic userBasic) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("denied",userBasic.getDenied());
        paramMap.put("deniedReason",userBasic.getDeniedReason());
        paramMap.put("ids",ids);
        return this.userBasicDAO.accessOrDeniedUser(paramMap);
    }

}
