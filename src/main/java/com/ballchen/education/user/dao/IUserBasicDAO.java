package com.ballchen.education.user.dao;

import com.ballchen.education.user.entity.UserBasic;

import java.util.List;
import java.util.Map;

public interface IUserBasicDAO {
    int deleteByPrimaryKey(String id);

    int insert(UserBasic record);

    int insertSelective(UserBasic record);

    UserBasic selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserBasic record);

    int updateByPrimaryKeyWithBLOBs(UserBasic record);

    int updateByPrimaryKey(UserBasic record);

    /**
     * 根据参数查询用户分页
     * @param queryMap 查询参数
     * @return List<UserBasic>
     */
    List<UserBasic> getUserBasicPagination(Map<String,Object> queryMap);

    /**
     * 根据参数查询用户总数
     * @param queryMap 查询参数
     * @return Long
     */
    Long getUserBasicPaginationCount(Map<String,Object> queryMap);

    /**
     * 根据ID获得用户带角色
     * @param id 用户Id
     * @return UserBasic
     */
    UserBasic selectUserBasicWithRolesByPrimaryKey(String id);

    /**
     * 根据ID删除用户
     * @param paramMap id数组
     * @return int
     */
     int deleteByIds(Map<String,Object> paramMap);

    /**
     * 禁用用户
     * @param paramMap id数组
     * @return int
     */
    int accessOrDeniedUser(Map<String,Object> paramMap);

    /**
     * 查询最新添加时间的用户
     * @param queryMap 查询参数
     * @return UserBasic
     */
    UserBasic selectFirstUserBasic(Map<String,Object> queryMap);

    /**
     * 根据身份证号查询用户
     * @param queryMap 身份证号
     * @return UserBasic
     */
    UserBasic selectUserBasicByIdNumber(Map<String,Object> queryMap);

    /**
     * 根据参数查询用户并且带账户，角色，头像
     * @param queryMap 查询参数
     * @return UserBasic
     */
    UserBasic selectUserBasicWithRolesAndHeadPictureAccessoryByPrimaryKey(Map<String,Object> queryMap);

}