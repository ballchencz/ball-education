package com.ballchen.education.address.service;

import com.ballchen.education.address.entity.Address;

/**
 * Created by ballchen on 2016/9/26.
 */
public interface IAddressService {
    int deleteByPrimaryKey(String id);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);
}
