package com.ballchen.education.category.service;

import com.ballchen.education.admin.entity.PageHelper;
import com.ballchen.education.category.entity.Category;

import java.util.List;

/**
 * Created by ballchen on 2016/7/27.
 */
public interface ICategoryService {

    /**
     * 获得分类管理分页数据
     * @param category 分类实体
     * @param pageHelper 分页参数类
     * @return List<Category>
     */
    List<Category> getCategoryPagination(Category category, PageHelper pageHelper);

    /**
     * 获得分类管理分类总数
     * @param category 分类实体
     * @param pageHelper 分页参数类
     * @return long
     */
    long getCategoryPaginationCount(Category category,PageHelper pageHelper);

    int deleteByPrimaryKey(String id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}
