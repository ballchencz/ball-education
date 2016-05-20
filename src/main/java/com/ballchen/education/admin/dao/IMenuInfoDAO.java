package com.ballchen.education.admin.dao;


import com.ballchen.education.admin.entity.MenuInfo;

public interface IMenuInfoDAO {
    int deleteByPrimaryKey(String id);

    int insert(MenuInfo record);

    int insertSelective(MenuInfo record);

    MenuInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MenuInfo record);

    int updateByPrimaryKey(MenuInfo record);
}