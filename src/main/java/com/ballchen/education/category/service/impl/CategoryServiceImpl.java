package com.ballchen.education.category.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ballchen.education.accessory.entity.Accessory;
import com.ballchen.education.accessory.service.IAccessoryService;
import com.ballchen.education.admin.consts.AdminConsts;
import com.ballchen.education.admin.entity.PageHelper;
import com.ballchen.education.admin.utils.AdminUtils;
import com.ballchen.education.category.dao.ICategoryDAO;
import com.ballchen.education.category.entity.Category;
import com.ballchen.education.category.service.ICategoryService;
import com.ballchen.education.security.entity.Role;
import com.ballchen.education.user.entity.UserBasic;
import com.ballchen.education.user.entity.UserBasicAccessory;
import com.ballchen.education.user.entity.UserBasicRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ballchen on 2016/7/27.
 */
@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryDAO categoryDAO;

    @Autowired
    private IAccessoryService accessoryService;


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

    @Override
    public List<Category> selectByParentId(String parentId) {
        return categoryDAO.selectByParentId(parentId);
    }


    @Override
    public JSONObject getCategoryPagination(Long total, List<Category> categories) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonObject.put("total",total);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AdminConsts.DATE_FORMAT_STRING);
        for(Category category : categories){
            JSONObject jsonObjectTemp = new JSONObject();
            jsonObjectTemp.put("id",category.getId());
            jsonObjectTemp.put("categoryName",category.getCategoryName());
            jsonObjectTemp.put("createTime",simpleDateFormat.format(category.getCreateTime()));
            jsonObjectTemp.put("categoryType",category.getCategoryType());
            if(category.getParentId()!=null){
                jsonObjectTemp.put("_parentId",category.getParentId());
            }
            List<Category> categories1 = this.selectByParentId(category.getId());
            if(categories1!=null&&categories1.size()>0){
                jsonObjectTemp.put("state","closed");
            }
            jsonArray.add(jsonObjectTemp);
        }
        jsonObject.put("rows",jsonArray.toArray());
        return jsonObject;
    }

    @Override
    public JSONArray getCategoryTree(List<Category> categories) {
        JSONArray jsonArray = new JSONArray();
        for(Category category : categories){
            JSONObject jsonObjectTemp = new JSONObject();
            jsonObjectTemp.put("id",category.getId());
            jsonObjectTemp.put("text",category.getCategoryName());
            if(category.getParentId()!=null){
                jsonObjectTemp.put("_parentId",category.getParentId());
            }
            List<Category> categories1 = this.selectByParentId(category.getId());
            if(categories1!=null&&categories1.size()>0){
                jsonObjectTemp.put("state","closed");
            }
            jsonArray.add(jsonObjectTemp);
        }
        return jsonArray;
    }

    @Override
    public int insertSelective(Category record, Accessory accessory) {
        //用户添加结果
        int categoryInsertResult = -1;
        if(record.getParentId()==null || record.getParentId().equals("")){
            record.setParentId(null);
        }
        if(accessory!=null){//如果附件不为空，说明用户上传了头像，添加附件，并和userBasic做关联
           this.accessoryService.insertSelective(accessory);
            record.setAccessoryId(accessory.getId());
        }
        categoryInsertResult = categoryDAO.insertSelective(record);
        return categoryInsertResult;
    }

    @Override
    public int updateByPrimaryKeySelective(Category record, Accessory accessory) {
        int i;
        /*------------------------------------------------------------------------*/
        /*---------------解除分类与附件的关系，添加新的关联（附件不为空的情况下）-------------*/
            Category categoryTemp = this.categoryDAO.selectByPrimaryKey(record.getId());
            String accessoryId = categoryTemp.getAccessoryId();
            if(categoryTemp!=null && accessory!=null){
                //首先把分类图片附件ID设置为空,修改分类
                categoryTemp.setAccessoryId(null);
                this.categoryDAO.updateByPrimaryKey(categoryTemp);
                //然后删除附件
                if(accessoryId!=null){
                    this.accessoryService.deleteByPrimaryKey(accessoryId);
                }
                //再然后添加附件
                if(accessory!=null){
                    this.accessoryService.insertSelective(accessory);
                }
                //然后再给分类设置附件ID
                record.setAccessoryId(accessory.getId());
            }
            if(record.getParentId()==null || record.getParentId().equals("")){
                record.setParentId(null);
            }
            i=categoryDAO.updateByPrimaryKeySelective(record);

        /*------------------------------------------------------------------------------------*/
        return  i;
    }

    @Override
    public List<Category> getCategoryBySelective(String [] categoryTypes) {
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("categoryTypes",categoryTypes);
        List<Category> categorys =categoryDAO.getCategoryBySelective(queryMap);
        return categorys;
    }
}
