package com.ballchen.education.account.service;

import com.ballchen.education.account.entity.Account;

import java.util.List;

/**
 * Created by ballchen on 2016/6/1.
 */
public interface IAccountService {
    /**
     * 获取账户分页数据
     * @param account 分页查询参数封装类
     * @return List<Account> 账户分页集合
     */
     List<Account> getAccountPagination(Account account);

    /**
     * 获得账户记录总数
     * @return Long
     */
    Long getAccountPaginationCount(Account account);

    /**
     * 添加账户
     * @param account 账户实体类
     * @return int
     */
    int insertAccount(Account account);

    /**
     * 修改账户
     * @param account 账户实体类
     * @return int
     */
    int updateAccount(Account account);


}
