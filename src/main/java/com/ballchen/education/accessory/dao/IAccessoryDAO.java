package com.ballchen.education.accessory.dao;

import com.ballchen.education.accessory.entity.Accessory;

import java.util.List;
import java.util.Map;

/**
 * Created by ballchen on 2016/7/19.
 */
public interface IAccessoryDAO {
    int deleteByPrimaryKey(String id);
    int deleteByPrimaryKeys(Map<String,Object> queryMap);

    int insert(Accessory record);

    int insertSelective(Accessory record);

    Accessory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Accessory record);

    int updateByPrimaryKey(Accessory record);

    List<Accessory> selectAccessoryByUserIdAndIdCardPicture(Map<String,Object> queryMap);
}
