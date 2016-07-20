package com.ballchen.education.user.service.impl;

import com.ballchen.education.accessory.entity.Accessory;
import com.ballchen.education.accessory.service.IAccessoryService;
import com.ballchen.education.admin.entity.PageHelper;
import com.ballchen.education.admin.utils.AdminUtils;
import com.ballchen.education.consts.PublicConsts;
import com.ballchen.education.security.dao.IRoleAuthorizationDAO;
import com.ballchen.education.security.entity.Role;
import com.ballchen.education.security.entity.RoleAuthorization;
import com.ballchen.education.security.service.IRoleService;
import com.ballchen.education.user.dao.IUserBasicAccessoryDAO;
import com.ballchen.education.user.dao.IUserBasicDAO;
import com.ballchen.education.user.entity.UserBasic;
import com.ballchen.education.user.entity.UserBasicAccessory;
import com.ballchen.education.user.entity.UserBasicRole;
import com.ballchen.education.user.service.IUserBasicAccessoryService;
import com.ballchen.education.user.service.IUserBasicRoleService;
import com.ballchen.education.user.service.IUserService;
import com.ballchen.education.utils.PublicUtils;
import com.ballchen.education.utils.SftpUtils;
import com.jcraft.jsch.SftpException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.accessibility.AccessibleRelation;
import java.io.IOException;
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
    @Autowired
    private IAccessoryService accessoryService;
    @Autowired
    private IUserBasicAccessoryService userBasicAccessoryService;

    @Override
    public int deleteByPrimaryKey(String id) {
        return userBasicDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserBasic record) {
        return userBasicDAO.insert(record);
    }

    @Override
    public int insertSelective(UserBasic record,Accessory accessory) {
        //附件添加结果
        int accessoryInsertResult = -1;
        //用户添加结果
        int userBasicInsertResult;
        userBasicInsertResult = userBasicDAO.insertSelective(record);
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
        if(accessory!=null){//如果附件不为空，说明用户上传了头像，添加附件，并和userBasic做关联
            accessoryInsertResult = this.accessoryService.insertSelective(accessory);
        }
        if(accessoryInsertResult>0 && userBasicInsertResult>0){
            UserBasicAccessory userBasicAccessory = this.userBasicAccessoryService.getUserBasicAccessoryByUserBasicAndAccessoryEntity(record,accessory);
            this.userBasicAccessoryService.insertSelective(userBasicAccessory);
        }
        return userBasicInsertResult;

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

    @Override
    public UserBasic selectFirstUserBasic() {
        return userBasicDAO.selectFirstUserBasic(PublicConsts.USER_FILE_TYPE_HEAD_PICTURE);
    }

    @Override
    public UserBasic selectUserBasicByIdNumber(String idNumber,String id) {
        Map<String,Object> queryMap= new HashMap<>();
        if(id!=null){
            queryMap.put("id",id);
        }
        if(idNumber!=null){
            queryMap.put("idNumber",idNumber);
        }
        return userBasicDAO.selectUserBasicByIdNumber(queryMap);
    }

}
