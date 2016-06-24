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
}