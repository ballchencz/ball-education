package com.ballchen.education.user.service;

import com.ballchen.education.accessory.entity.Accessory;
import com.ballchen.education.user.entity.UserBasic;
import com.ballchen.education.user.entity.UserBasicAccessory;

/**
 * Created by ballchen on 2016/7/20.
 * 用户--附件中间表service接口
 */
public interface IUserBasicAccessoryService {
    int deleteByPrimaryKey(String id);

    int insert(UserBasicAccessory record);

    int insertSelective(UserBasicAccessory record);

    UserBasicAccessory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserBasicAccessory record);

    int updateByPrimaryKey(UserBasicAccessory record);

    UserBasicAccessory getUserBasicAccessoryByUserBasicAndAccessoryEntity(UserBasic userBasic, Accessory accessory);

    UserBasicAccessory getUserBasicAccessoryByUserBasicIdAndAccessoryId(String userBasicId,String accessoryId);
}
