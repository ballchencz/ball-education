package com.ballchen.education.user.service;

import com.ballchen.education.accessory.entity.Accessory;
import com.ballchen.education.admin.entity.PageHelper;
import com.ballchen.education.user.entity.UserBasic;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by ChenZhao on 2016/5/19.
 */
public interface IUserService {
    /**
     * 根据ID删除用户
     * @param id
     * @return int
     */
    int deleteByPrimaryKey(String id);

    /**
     * 添加用户
     * @param record
     * @return int
     */
    int insert(UserBasic record);

    /**
     * 添加用户
     * @param record
     * @return int
     */
    int insertSelective(UserBasic record, Accessory accessory);

    /**
     * 根据ID查询用户
     * @param id
     * @return UserBasic
     */
    UserBasic selectByPrimaryKey(String id);

    /**
     * 修改用户
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(UserBasic record);

    /**
     * 修改用户
     * @param record
     * @return int
     */
    int updateByPrimaryKeyWithBLOBs(UserBasic record);

    /**
     * 根据ID修改用户
     * @param record
     * @return int
     */
    int updateByPrimaryKey(UserBasic record);

    /**
     * 根据参数查询用户分页
     * @param userBasic 用户对象
     * @param pageHelper 分页对象
     * @return List<UserBasic>
     */
    List<UserBasic> getUserBasicPagination(UserBasic userBasic, PageHelper pageHelper);

    /**
     * 根据参数查询用户总数
     * @param userBasic 用户对象
     * @param pageHelper 分页对象
     * @return Long
     */
    Long getUserBasicPaginationCount(UserBasic userBasic, PageHelper pageHelper);

    /**
     * 根据ID获得用户带角色
     * @param id 用户ID
     * @return UserBasic
     */
    UserBasic selectUserBasicWithRolesByPrimaryKey(String id);

    /**
     * 根据ID删除用户
     * @param ids id数组
     * @return int
     */
    int deleteByIds(String [] ids);

    /**
     * 禁用用户
     * @param ids id数组
     * @param userBasic 禁用信息
     * @return int
     */
    int accessOrDeniedUser(String [] ids,UserBasic userBasic);

    /**
     * 查询最新添加时间的用户
     * @return UserBasic
     */
    UserBasic selectFirstUserBasic();

    /**
     * 根据身份证号查询用户
     * @param idNumber 身份证号
     * @return UserBasic
     */
    UserBasic selectUserBasicByIdNumber(String idNumber,String id);
}
