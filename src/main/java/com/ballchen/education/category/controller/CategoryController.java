package com.ballchen.education.category.controller;

import com.alibaba.fastjson.JSONArray;
import com.ballchen.education.admin.consts.AdminConsts;
import com.ballchen.education.admin.entity.PageHelper;
import com.ballchen.education.category.consts.CategoryConst;
import com.ballchen.education.category.entity.Category;
import com.ballchen.education.category.service.ICategoryService;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ballchen on 2016/7/28.
 */
@Controller
@RequestMapping(value = "categoryController")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;
    /**
     * 获得分类类型数据
     * @return 分类类型
    */
    @RequestMapping(value = "/getCategoryTypeJSON",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getCategoryTypesJSON(){
        return CategoryConst.categoryTypes;
    }

    /**
     * 根据父ID获得分类
     * @return List<Category>
     */
    @RequestMapping(value = "/getCategoryByParentId",method = RequestMethod.GET)
    @ResponseBody
    public String getCategoryByParentId(String parentId){
        List<Category> categories = categoryService.selectByParentId(parentId);
        String s = JSONArray.toJSONStringWithDateFormat(categories, AdminConsts.DATE_FORMAT_STRING);
        return s;
    }

    @RequestMapping(value = "/getCategoryAsyncTree",method = RequestMethod.GET)
    @ResponseBody
    public String getCategoryAsyncTree(Category category,PageHelper pageHelper){
        if(category.getId()!=null){
            String parentId = category.getId();
            category.setParentId(parentId);
            category.setId(null);
        }
        List<Category> categories = this.categoryService.getCategoryPagination(category,pageHelper);
        return this.categoryService.getCategoryTree(categories).toJSONString();
    }


    @RequestMapping(value = "/getCategoryById",method = RequestMethod.GET)
    @ResponseBody
    public Category getCategoryById(String id){
        return this.categoryService.selectByPrimaryKey(id);
    }


    /**
     * 获得所有课程分类
     * @return
     */
    @RequestMapping(value = "/getAllCourseCategory",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public List<Category> getAllCourseCategory(){
        String [] categoryTypes = {
                CategoryConst.CATEGORY_TYPE_SELF_COURSE,
                CategoryConst.CATEGORY_TYPE_TEACHER_COURSE,
                CategoryConst.CATEGORY_TYPE_ORGANIZATION_COURSE
        };
        List<Category> categories =  categoryService.getCategoryBySelective(categoryTypes);
        return categories;
    }
}
