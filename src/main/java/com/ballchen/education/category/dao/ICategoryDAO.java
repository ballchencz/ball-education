package com.ballchen.education.category.dao;

import com.ballchen.education.category.entity.Category;

import java.util.List;
import java.util.Map;

public interface ICategoryDAO {
    int deleteByPrimaryKey(String id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> getCategoryPagination(Map<String,Object> queryMap);

    long getCategoryPaginationCount(Map<String,Object> queryMap);
}