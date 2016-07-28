package com.ballchen.education.category.service.impl;

import com.ballchen.education.admin.entity.PageHelper;
import com.ballchen.education.admin.utils.AdminUtils;
import com.ballchen.education.category.dao.ICategoryDAO;
import com.ballchen.education.category.entity.Category;
import com.ballchen.education.category.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by ballchen on 2016/7/27.
 */
@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryDAO categoryDAO;

    @Override
    public List<Category> getCategoryPagination(Category category, PageHelper pageHelper) {
        Map<String,Object> queryMap = AdminUtils.parsePOJOtoMap(category);
        queryMap.putAll(AdminUtils.parsePOJOtoMap(pageHelper));
        return categoryDAO.getCategoryPagination(queryMap);
    }

    @Override
    public long getCategoryPaginationCount(Category category,PageHelper pageHelper) {
        Map<String,Object> queryMap = AdminUtils.parsePOJOtoMap(category);
        queryMap.putAll(AdminUtils.parsePOJOtoMap(pageHelper));
        return categoryDAO.getCategoryPaginationCount(queryMap);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return categoryDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Category record) {
        return categoryDAO.insert(record);
    }

    @Override
    public int insertSelective(Category record) {
        return categoryDAO.insertSelective(record);
    }

    @Override
    public Category selectByPrimaryKey(String id) {
        return categoryDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Category record) {
        return categoryDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Category record) {
        return categoryDAO.updateByPrimaryKey(record);
    }
}
