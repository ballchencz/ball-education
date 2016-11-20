package com.ballchen.education.address.service.impl;

import com.ballchen.education.address.dao.IAddressDAO;
import com.ballchen.education.address.entity.Address;
import com.ballchen.education.address.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ballchen on 2016/9/26.
 */
@Service
@Transactional
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private IAddressDAO addressDAO;

    @Override
    public int deleteByPrimaryKey(String id) {
        return addressDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Address record) {
        return addressDAO.insert(record);
    }

    @Override
    public int insertSelective(Address record) {
        return addressDAO.insertSelective(record);
    }

    @Override
    public Address selectByPrimaryKey(String id) {
        return addressDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Address record) {
        return addressDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Address record) {
        return addressDAO.updateByPrimaryKey(record);
    }
}
