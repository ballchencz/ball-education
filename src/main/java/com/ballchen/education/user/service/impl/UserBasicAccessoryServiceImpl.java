package com.ballchen.education.user.service.impl;

import com.ballchen.education.accessory.entity.Accessory;
import com.ballchen.education.user.dao.IUserBasicAccessoryDAO;
import com.ballchen.education.user.entity.UserBasic;
import com.ballchen.education.user.entity.UserBasicAccessory;
import com.ballchen.education.user.service.IUserBasicAccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ballchen on 2016/7/20.
 */
@Service
@Transactional
public class UserBasicAccessoryServiceImpl implements IUserBasicAccessoryService {

    @Autowired
    private IUserBasicAccessoryDAO userBasicAccessoryDAO;

    @Override
    public int deleteByPrimaryKey(String id) {
        return userBasicAccessoryDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserBasicAccessory record) {
        return userBasicAccessoryDAO.insert(record);
    }

    @Override
    public int insertSelective(UserBasicAccessory record) {
        return userBasicAccessoryDAO.insertSelective(record);
    }

    @Override
    public UserBasicAccessory selectByPrimaryKey(String id) {
        return userBasicAccessoryDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserBasicAccessory record) {
        return userBasicAccessoryDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserBasicAccessory record) {
        return userBasicAccessoryDAO.updateByPrimaryKey(record);
    }

    @Override
    public UserBasicAccessory getUserBasicAccessoryByUserBasicAndAccessoryEntity(UserBasic userBasic, Accessory accessory) {
        UserBasicAccessory userBasicAccessory = null;
        if(userBasic!=null && accessory!=null){
            userBasicAccessory = new UserBasicAccessory(UUID.randomUUID().toString(),null,userBasic.getId(),accessory.getId());
        }
        return userBasicAccessory;
    }

    @Override
    public UserBasicAccessory getUserBasicAccessoryByUserBasicIdAndAccessoryId(String userBasicId, String accessoryId) {
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("userBasicId",userBasicId);
        queryMap.put("accessoryId",accessoryId);
        return userBasicAccessoryDAO.getUserBasicAccessoryByUserBasicIdAndAccessoryId(queryMap);
    }
}
