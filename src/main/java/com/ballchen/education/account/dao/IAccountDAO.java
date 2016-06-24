package com.ballchen.education.account.dao;

import com.ballchen.education.account.entity.Account;

import java.util.List;
import java.util.Map;

public interface IAccountDAO {
    int deleteByPrimaryKey(String id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    List<Account> getAccountPagination(Account account);

    long getAccountPaginationCount(Account account);

    int accessOrDeniedAccount(Map<String,Object> queryMap);

    int deleteByIds(Map<String,Object> queryMap);

    List<Account> getAllAccount();
}