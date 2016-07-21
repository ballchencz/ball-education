package com.ballchen.education.user.dao;

import com.ballchen.education.user.entity.UserBasicAccessory;

import java.util.Map;

/**
 * Created by ballchen on 2016/7/20.
 * 用户--附件中间表DAO
 */
public interface IUserBasicAccessoryDAO {
    int deleteByPrimaryKey(String id);

    int insert(UserBasicAccessory record);

    int insertSelective(UserBasicAccessory record);

    UserBasicAccessory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserBasicAccessory record);

    int updateByPrimaryKey(UserBasicAccessory record);

    UserBasicAccessory getUserBasicAccessoryByUserBasicIdAndAccessoryId(Map<String,Object> queryMap);
}
