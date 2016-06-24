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

    /**
     * 根据id查询账户
     * @param id 账户ID
     * @return Account 账户实体
     */
    Account getAccountById(String id);

    /**
     * 禁用或启用账户
     * @param ids 账户ID数组
     * @return int
     */
    int accessOrDeniedAccount(String [] ids,boolean denied);

    /**
     * 删除账户
     * @param ids 根据ID删除账户
     * @return int
     */
    int deleteByIds(String [] ids);

    /**
     * 获得所有账户（用户中没有的）
     * @return List<Account>
     */
    List<Account> getAllAccount();


}
