package com.ballchen.education.account.service.impl;

import com.ballchen.education.account.dao.IAccountDAO;
import com.ballchen.education.account.entity.Account;
import com.ballchen.education.account.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return accountDAO.insert(account);
    }

    @Override
    public int updateAccount(Account account) {
        return accountDAO.updateByPrimaryKeySelective(account);
    }
}
