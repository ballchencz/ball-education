package com.ballchen.education.account.dao;

import com.ballchen.education.account.entity.Account;

public interface IAccountDAO {
    int deleteByPrimaryKey(String id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}