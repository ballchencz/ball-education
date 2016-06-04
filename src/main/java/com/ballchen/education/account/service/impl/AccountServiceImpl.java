package com.ballchen.education.account.service.impl;

import com.ballchen.education.account.dao.IAccountDAO;
import com.ballchen.education.account.entity.Account;
import com.ballchen.education.account.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ballchen on 2016/6/1.
 */
@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDAO accountDAO;

    @Override
    public List<Account> getAccountPagination(Account account) {
        return accountDAO.getAccountPagination(account);
    }

    @Override
    public Long getAccountPaginationCount(Account account) {
        return accountDAO.getAccountPaginationCount(account);
    }

    @Override
    public int insertAccount(Account account) {
        return accountDAO.insertSelective(account);
    }

    @Override
    public int updateAccount(Account account) {
        return accountDAO.updateByPrimaryKeySelective(account);
    }

    @Override
    public Account getAccountById(String id) {
        return accountDAO.selectByPrimaryKey(id);
    }

    @Override
    public int accessOrDeniedAccount(String[] ids,boolean denied) {
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("ids",ids);
        queryMap.put("denied",denied);
        return accountDAO.accessOrDeniedAccount(queryMap);
    }

    @Override
    public int deleteByIds(String[] ids) {
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("ids",ids);
        return accountDAO.deleteByIds(queryMap);
    }
}
