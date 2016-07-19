package com.ballchen.education.accessory.dao;

import com.ballchen.education.accessory.entity.Accessory;

/**
 * Created by ballchen on 2016/7/19.
 */
public interface IAccessoryDAO {
    int deleteByPrimaryKey(String id);

    int insert(Accessory record);

    int insertSelective(Accessory record);

    Accessory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Accessory record);

    int updateByPrimaryKey(Accessory record);
}
