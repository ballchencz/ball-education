package com.ballchen.education.category.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ballchen.education.accessory.entity.Accessory;
import com.ballchen.education.admin.entity.PageHelper;
import com.ballchen.education.category.entity.Category;
import org.springframework.web.multipart.MultipartFile;

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

    List<Category> selectByParentId(String parentId);


    JSONObject getCategoryPagination(Long total,List<Category> categories);

    JSONArray getCategoryTree(List<Category> categories);

    int insertSelective(Category record, Accessory accessory);

    /**
     * 修改用户
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Category record,Accessory accessory);

    /**
     * 根据条件查询
     * @param categoryType
     * @return List<Category>
     */
    List<Category> getCategoryBySelective(String [] categoryType);

}
